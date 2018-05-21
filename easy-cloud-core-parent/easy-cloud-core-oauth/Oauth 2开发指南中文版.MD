
# OAuth 2开发人员指南

## 介绍

这是 [`OAuth 2.0`](http://tools.ietf.org/html/draft-ietf-oauth-v2)支持的用户指南. 对于 OAuth 1.0来说, 一切都是不同的, 所以 [see its user guide](oauth1.html).

这个用户指南分为两个部分，第一个是OAuth 2.0提供者，第二个是OAuth 2.0客户端。 对于提供者和客户端， 示例代码的最佳来源是 [integration tests](https://github.com/spring-projects/spring-security-oauth/tree/master/tests) 和[sample apps](https://github.com/spring-projects/spring-security-oauth/tree/master/samples/oauth2).

## OAuth 2.0 提供者

OAuth 2.0提供者机制负责公开OAuth 2.0受保护的资源。配置包括建立OAuth 2.0客户端，可以独立地或代表用户访问其受保护的资源。提供者通过管理和验证用于访问受保护资源的OAuth 2.0令牌来实现这一点。在适用的情况下，提供者还必须为用户提供一个接口，以确认客户端可以访问受保护的资源(即确认页面)。

## OAuth 2.0 提供者实现类

OAuth 2.0中的提供者角色实际上是在授权服务和资源服务之间进行划分的，虽然它们有时是在同一个应用程序中，但在Spring Security OAuth中，您可以选择在两个应用程序之间进行拆分，并且拥有多个共享授权服务的资源服务。令牌的请求由Spring MVC控制器端点来处理，而对受保护资源的访问由标准Spring安全请求过滤器处理。为了实现OAuth 2.0授权服务器，Spring安全过滤器链中需要以下端点:

* [`AuthorizationEndpoint`][AuthorizationEndpoint] 用于服务请求的授权。默认URL: `/oauth/authorize`.
* [`TokenEndpoint`][TokenEndpoint] 用于服务访问令牌的请求。默认URL: `/oauth/token`.

下面的过滤器需要实现OAuth 2.0资源服务器:

*  [`OAuth2AuthenticationProcessingFilter`][OAuth2AuthenticationProcessingFilter] 用于为请求提供一个经过身份验证的访问令牌进行身份验证。

对于所有OAuth 2.0提供者特性， 可以使用特殊的Spring OAuth `@Configuration` 适配器配置简化配置 .  还有一个用于OAuth配置的XML命名空间, 这个模式在 [http://www.springframework.org/schema/security/spring-security-oauth2.xsd][oauth2.xsd]. 命名空间是 `http://www.springframework.org/schema/security/oauth2`.

## 授权服务器配置

在配置授权服务器时，您必须考虑客户端用于从最终用户(例如授权代码、用户凭证、刷新令牌)中获得访问令牌的授权类型。 服务器的配置用于提供客户端详细信息服务和令牌服务的实现，并在全局范围内启用或禁用该机制的某些切面。但是，请注意，每个客户端都可以配置特定的权限，以便能够使用某些授权机制和访问授权。也就是说，仅仅因为您的提供者被配置为支持“客户端凭证”授予类型，并不意味着特定的客户端被授权使用该授予类型。

@EnableAuthorizationServer注释用于配置OAuth 2.0授权服务器机制，以及任何实现AuthorizationServerConfigurer的@ bean(有一个方便的适配器实现提供了空方法的实现) 。下面的特性被委托给由Spring创建的配置器，并传递给AuthorizationServerConfigurer`:

* `ClientDetailsServiceConfigurer`: 定义客户端详细信息服务的配置程序。客户端细节可以被初始化，也可以直接引用现有的存储。
* `AuthorizationServerSecurityConfigurer`: 定义令牌端点上的安全约束。
* `AuthorizationServerEndpointsConfigurer`: 定义授权和令牌端点和令牌服务。

提供者配置的一个重要方面是授权代码被提供给OAuth客户端（在授权代码授予中）。授权代码由OAuth客户端获得，它将终端用户引导到一个授权页面，用户可以在其中输入她的凭证，从而导致从提供者授权服务器重新定向到带有授权代码的OAuth客户端。在OAuth 2规范中详细说明了这一点。

在XML中，有一个< authorizationserver />元素，它以类似的方式用于配置OAuth 2.0授权服务器。

### 配置客户端详细信息

ClientDetailsServiceConfigurer(来自您的AuthorizationServerConfigurer的回调)可以用于定义客户端详细信息服务的内存或JDBC实现。客户端的重要属性是:

* `clientId`:(必需)客户id。
* `secret`: (需要信任的客户)客户的密钥，如果有的话。
* `scope`: 客户受限制的范围。如果作用域是未定义的或空的(默认的)，客户端不受范围限制。
* `authorizedGrantTypes`: 授权给客户端使用的授权类型。默认值是空的。
* `authorities`: 授权给客户的部门。(通常是 Spring Security authorities).

客户端详细信息可以在运行的应用程序中更新，通过直接访问底层存储（例如JdbcClientDetailsService案例中的数据库表）或通过ClientDetailsManager接口（ClientDetailsService的两个实现都实现了）。

> 注意:JDBC服务的模式并没有与库一起打包(因为在实践中可能会使用太多的变体)，但是有一个例子可以从 [test code in github开始。

### 管理令牌

 [`AuthorizationServerTokenServices`][AuthorizationServerTokenServices] 接口定义了管理OAuth 2.0令牌所必需的操作。 请注意以下几点:

* 当创建访问令牌时，必须存储身份验证，以便稍后接受访问令牌的资源可以引用它。
* 访问令牌被用来加载用于授权其创建的身份验证。

在创建您的AuthorizationServerTokenServices实现时，您可能需要考虑使用具有许多策略的DefaultTokenServices来更改访问令牌的格式和存储。 默认情况下，它通过随机值创建令牌，并处理所有的东西，除了它委托给TokenStore的令牌的持久性。默认存储是[在内存中实现的][InMemoryTokenStore]，但还有一些其他实现可用。下面是对每种实现方式的一些讨论。

* 默认的`InMemoryTokenStore`对于单个服务器来说是完美的(例如，在失败的情况下，低流量和没有热交换到备份服务器)。大多数项目都可以从这里开始，并可能在开发模式中使用这种方式，从而能很容易的启动一个没有依赖关系的服务器。

* `JdbcTokenStore`和JDBC版本是同一种东西，它使用关系数据库来存储令牌数据。如果您可以在服务器之间共享一个数据库，那么可以使用JDBC版本，如果只有一个服务器，则可以扩展相同服务器的实例，如果有多个组件，则可以使用授权和资源服务器。为了使用 `JdbcTokenStore` ，您需要将 "spring-jdbc" 配置到classpath中.

* [JSON Web Token (JWT) version](`JwtTokenStore`) 将所有关于grant的数据编码到令牌本身(因此没有任何后端存储，这是一个重要的优势)。 一个缺点是，您不能很容易地撤销访问令牌，因此它们通常在短时间内被授予，而撤销则在刷新令牌中处理。 另一个缺点是，如果您在其中存储了大量用户凭证信息，则令牌可以变得相当大。 `JwtTokenStore`并不是真正的“存储”，因为它没有保存任何数据，但是它在`DefaultTokenServices`中扮演了转换betweeen令牌值和身份验证信息的角色。

> 注意：JDBC服务的模式并没有与库一起打包（因为在实践中可能会用到太多的变体），但是有一个示例可以从github的 [test code in github开始。确保`@EnableTransactionManagement` 能够防止客户端应用程序在创建令牌时争用相同的行。 还要注意，示例模式有显式的主键声明——在并发环境中这些声明也是必需的。

### JWT令牌

要使用JWT令牌，您需要在授权服务器中使用`JwtTokenStore`。资源服务器还需要能够解码令牌，这样`JwtTokenStore` 就依赖于 `JwtAccessTokenConverter`，并且授权服务器和资源服务器都需要相同的实现。该令牌是默认签名的，并且资源服务器还必须能够验证签名，因此需要与授权服务器(共享私钥或对称密钥)相同的对称(签名)密钥，或者它需要与授权服务器(公私或非对称密钥)中的私钥(签名密钥)相匹配的公钥(验证器密钥)。公钥(如果可用)由`/oauth/token_key`端点上的授权服务器公开，该端点在默认情况下是安全的，具有访问规则“denyAll()”。您可以通过向`AuthorizationServerSecurityConfigurer`中注入标准的SpEL表达式来打开它。“permitAll（）”可能已经足够了，因为它是一个公钥。

要使用`JwtTokenStore` ，您需要在classpath上使用“Spring -security-jwt”(您可以在相同的github存储库中找到它，它与Spring OAuth相同，但有不同的发布周期)。

### 授权类型

 `AuthorizationEndpoint` 支持的授权类型可以通过`AuthorizationServerEndpointsConfigurer`配置。默认情况下，除密码外，所有的授权类型都是受支持的(请参阅下面关于如何切换的详细信息)。以下属性影响授权类型:

* `authenticationManager`: 通过注入一个`AuthenticationManager`来打开密码授权。
* `userDetailsService`: 如果您注入了一个`UserDetailsService`，或者在全局上配置了一个(例如，在`GlobalAuthenticationManagerConfigurer`中)，那么刷新令牌授权将包含对用户详细信息的检查，以确保帐户仍然处于活动状态。
* `authorizationCodeServices`: 为身份验证代码授予定义授权代码服务(`AuthorizationCodeServices`)的实例)。
* `implicitGrantService`: 在imlpicit授权期间管理状态。
* `tokenGranter`: the `TokenGranter` (完全控制授予和忽略上面的其他属性)

在XML grant类型中，包括`authorization-server`元素。

### 配置的端点url

`AuthorizationServerEndpointsConfigurer` 有一个`pathMapping()`方法。它需要两个参数:

* 端点的默认(框架实现)URL路径。
* 需要的自定义路径(以“/”开头)

框架提供的路径是 `/oauth/authorize` (授权端点), `/oauth/token` (令牌端点), `/oauth/confirm_access` (用户在这里获得批准), `/oauth/error` (用于呈现在授权服务器错误), `/oauth/check_token` (用于资源服务器解码访问令牌。), and `/oauth/token_key` (如果使用JWT令牌，公开公钥进行令牌验证).

N.B. 应该使用Spring Security保护授权端点`/oauth/authorize`(或其映射的替代)，以便只有经过身份验证的用户才能访问它。 例如：使用标准的 Spring Security `WebSecurityConfigurer`:

```
@Override
protected void configure(HttpSecurity http) throws Exception {
	http
            .authorizeRequests().antMatchers("/login").permitAll().and()
        // default protection for all resources (including /oauth/authorize)
            .authorizeRequests()
            .anyRequest().hasRole("USER")
        // ... more configuration, e.g. for form login
}
```

> 注意:如果您的授权服务器也是一个资源服务器，那么还有另一个安全过滤器链，它的优先级较低，控制了API资源。 对于那些需要通过访问令牌来保护的请求，您需要它们的路径不能与主用户所面对的过滤器链中的那些相匹配，所以一定要包含一个请求matcher，它只挑选出上面的`WebSecurityConfigurer`中的非Api资源。

在`@Configuration`支持中，使用客户端机密的HTTP基本身份验证，Spring OAuth默认为您保护令牌端点。这不是XML中的情况(因此应该明确地保护它)。

在XML中，`<authorization-server/>` 元素有一些属性可用于以类似的方式更改缺省端点url。必须显式地启用`/check_token` 端点(使用`check-token-enabled` 的属性)。

## 定制用户界面

大多数授权服务器端点主要是由机器使用的，但是有一些资源需要一个UI，而这些资源是GET for `/oauth/confirm_access`和 `/oauth/error`的HTML响应。 它们在框架中使用了whitelabel实现，因此授权服务器的大多数真实实例都希望提供自己的实现，这样它们就可以控制样式和内容。 您所需要做的就是为这些端点提供一个带有`@RequestMappings`的Spring MVC控制器，而框架默认值将在调度程序中降低优先级。 在`/oauth/confirm_access`端点中，您可以预期一个`AuthorizationRequest`绑定到会话，该请求将携带需要获得用户批准的所有数据(默认的实现是`AuthorizationRequest`，因此要查看那里的起始点以复制)。您可以从该请求中获取所有的数据，并按您喜欢的方式呈现它，然后所有用户需要做的就是将批准或拒绝授予的信息发布回 `/oauth/authorize`。请求参数直接传递给`AuthorizationEndpoint`中的`UserApprovalHandler`，这样您就可以随意地解释数据了。 默认的`UserApprovalHandler`取决于您是否在`AuthorizationServerEndpointsConfigurer`中提供了`ApprovalStore`（在这种情况下，它是`ApprovalStoreUserApprovalHandler`）或not（在这种情况下，它是一个`TokenStoreUserApprovalHandler`）。标准审批处理程序接受以下内容:

* `TokenStoreUserApprovalHandler`: 通过`user_oauth_approval`的一个简单的yes/no决策等于“true”或“false”。

* `ApprovalStoreUserApprovalHandler`: 一组`scope.*` 参数键与所请求的作用域相等。 参数的值可以是“true”或“approved”（如果用户批准了授权），则用户被认为拒绝了该范围。 如果至少有一个范围被批准，那么授权是成功的。

> 注意:不要忘记将CSRF保护包含在您为用户呈现的表单中。Spring Security在默认情况下期望一个名为“_csrf”的请求参数(它提供了请求属性中的值)。 请参阅Spring安全用户指南以获得更多信息，或者查看whitelabel实现以获得指导。

### 执行SSL

普通HTTP可以用于测试，但是授权服务器只能在生产中使用SSL。 您可以在一个安全的容器或代理的后面运行该应用程序，如果您正确地设置了代理和容器(这与OAuth2无关)，那么它应该可以正常工作。 您还可能希望使用Spring Security `requiresChannel()`约束来保护端点。 对于`/authorize`端点，你要做的是作为你正常的应用程序安全的一部分。对于`/token`端点，在`AuthorizationServerSecurityConfigurer` 中有一个标记，您可以使用`sslOnly()`方法进行设置。在这两种情况下，安全通道设置都是可选的，但如果它在不安全的通道上检测到请求，则会导致Spring Security重定向到它认为是安全通道的安全通道。

## 自定义错误处理

授权服务器中的错误处理使用标准的Spring MVC特性，即端点中的`@ExceptionHandler`方法。 用户还可以为端点本身提供一个`WebResponseExceptionTranslator`，这是改变响应内容的最佳方式，而不是改变响应的方式。在授权端点的情况下，在令牌端点和OAuth错误视图(`/oauth/error`)的情况下，将异常委托委托给`HttpMesssageConverters`(可以添加到MVC配置)。为HTML响应提供了whitelabel错误端点，但是用户可能需要提供一个自定义实现(例如，只需添加一个`@RequestMapping("/oauth/error")`的 `@Controller`)。

## 将用户角色映射到作用域。

有时，限制令牌的范围不仅限于分配给客户端的作用域，还可以根据用户自己的权限来限制令牌的范围。 如果您在`AuthorizationEndpoint`中使用`DefaultOAuth2RequestFactory` ，那么您可以设置一个flag  `checkUserScopes=true` ，从而将允许范围限制为与用户角色相匹配的范围。 您还可以将`OAuth2RequestFactory`注入到`TokenEndpoint`中，但是如果您还安装了一个`TokenEndpointAuthenticationFilter`(也就是使用密码授权)，那么您只需要在HTTP `BasicAuthenticationFilter`之后添加那个过滤器。当然，您也可以实现自己的规则，将范围映射到角色，并安装您自己的`OAuth2RequestFactory`版本。如果您使用`@EnableAuthorizationServer`,则`AuthorizationServerEndpointsConfigurer`允许您注入自定义的`OAuth2RequestFactory` ，这样您就可以使用该特性来设置一个工厂。

## 资源服务器配置

资源服务器(可以与授权服务器或单独的应用程序相同)提供由OAuth2令牌保护的资源。 Spring OAuth提供了一个实现此保护的Spring安全身份验证过滤器。您可以在 `@Configuration`类上使用`@EnableResourceServer` 来切换它，并使用`ResourceServerConfigurer`配置它（必要时）。他可以配置以下功能:

* `tokenServices`: 定义令牌服务的bean (ResourceServerTokenServices实例)。
* `resourceId`: 资源的id(可选，如果存在推荐并将由auth服务器验证)。
* 资源服务器的其他扩展点(例如从传入请求中提取令牌的`tokenExtractor`)。
* 请求受保护资源的请求者(默认为所有)
* 受保护资源的访问规则(默认为“已验证”)
* Spring Security中`HttpSecurity`配置程序允许的受保护资源的其他定制。

 `@EnableResourceServer`注解自动将`OAuth2AuthenticationProcessingFilter`类型的过滤器添加到Spring Security过滤器链中。

在XML中有一个带有id属性的`<resource-server/>`元素——这是servlet过滤器的bean id，然后可以手工添加到标准Spring Security链中。

您的`ResourceServerTokenServices`是与授权服务器的契约的另一半。如果资源服务器和授权服务器都在同一个应用程序中，并且使用`DefaultTokenServices` ，那么您就不必对此进行过多的思考，因为它实现了所有必要的接口，因此它是自动一致的。 如果您的资源服务器是一个单独的应用程序，那么您必须确保与授权服务器的功能相匹配，并提供一个知道如何正确解码`ResourceServerTokenServices`。与授权服务器一样，您可以经常使用 `DefaultTokenServices`，而选择主要通过`TokenStore`(后端存储或本地编码)来表示。另一种选择是`RemoteTokenServices` ，它是一个Spring OAuth特性(不是规范的一部分)，允许资源服务器通过授权服务器上的HTTP资源(`/oauth/check_token`)来解码令牌。如果资源服务器中没有大量的流量(每个请求都必须与授权服务器进行验证)，或者如果您有能力缓存结果，那么`RemoteTokenServices`是很方便的。 要使用`/oauth/check_token`端点，您需要在`AuthorizationServerSecurityConfigurer`中通过更改它的访问规则来公开它(默认为“denyAll()”)。例如

```
@Override
public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
	oauthServer.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')")
	.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
}
```

在这个例子中，我们配置了`/oauth/check_token` 端点和`/oauth/token_key`端点（因此可信资源可以获得JWT验证的公钥）。这两个端点通过使用客户端凭证的HTTP基本身份验证保护。

### 配置一个oauthaware表达式处理程序。

您可能想要利用Spring Security的基于表达式的访问控制。表达式处理程序将默认在@enableresourceserver设置中注册。 表达式包括 _#oauth2.clientHasRole_, _#oauth2.clientHasAnyRole_, 和_#oath2.denyClient_ 可以根据oauth客户端的角色来提供访问(请参阅完整列表的 `OAuth2SecurityExpressionMethods` )。在XML中，您可以使用常规的`<http/>`安全配置的 `expression-handler`程序元素注册一个oauth-aware表达式处理程序。

## OAuth 2.0客户端

OAuth 2.0客户端机制负责访问其他服务器的OAuth 2.0保护资源。 该配置涉及到建立用户可能访问的相关保护资源。 客户端还可能需要为用户提供存储授权代码和访问令牌的机制。

### 受保护的资源配置

可以使用[`OAuth2ProtectedResourceDetails的bean定义来定义受保护的资源(或“远程资源”)。受保护的资源具有以下属性:

* `id`: 资源的id。该id仅供客户端用于查找资源;在OAuth协议中从未使用过。它还被用作bean的id。
* `clientId`: OAuth客户端id。这是OAuth提供者识别您的客户端的id。
* `clientSecret`: 与资源有关的secret。默认情况下，没有secret是空的。
* `accessTokenUri`: 提供访问令牌的提供者OAuth端点的URI。
* `scope`: 逗号分隔的字符串列表，指定访问资源的范围。默认情况下，没有指定范围。
* `clientAuthenticationScheme`: 客户端用于验证访问令牌端点的方案。建议值:“http_basic”和“form”。默认值:“http_basic”。见OAuth 2规范第2.1节。

不同的grant类型有不同的`OAuth2ProtectedResourceDetails`的具体实现(例如，`ClientCredentialsResource` 用于“client_credentials”授权类型)。对于需要用户授权的授权类型，还有一个属性:

* `userAuthorizationUri`: 如果用户需要授权访问该资源，则将重定向用户的uri。注意，这并不总是必需的，这取决于所支持的OAuth 2配置文件。

在XML中，有一个`<resource/>`元素，可以用来创建一个`OAuth2ProtectedResourceDetails`的bean。它具有匹配上述所有属性的属性。


### 客户端配置

对于OAuth 2.0客户端，配置是使用`@EnableOAuth2Client`简化的。它做了两件事:

* 创建一个过滤器bean(带有ID `oauth2ClientContextFilter`)来存储当前请求和上下文。在需要进行身份验证的情况下，它管理对OAuth身份验证uri的重定向。

* 在请求范围内创建一个类型`AccessTokenRequest`的bean。这可以通过授权代码(或隐式)授予客户端来保持与单个用户之间的冲突。

过滤器必须连接到应用程序中(例如，使用Servlet初始化器或使用相同名称的`DelegatingFilterProxy`的`web.xml`配置)。

`AccessTokenRequest`可用于以下的`OAuth2RestTemplate`:

```
@Autowired
private OAuth2ClientContext oauth2Context;

@Bean
public OAuth2RestTemplate sparklrRestTemplate() {
	return new OAuth2RestTemplate(sparklr(), oauth2Context);
}
```

OAuth2ClientContext在会话范围内(为您)放置，以保持状态不同的用户分开。如果不这样做，您将不得不在服务器上管理等效的数据结构，将传入的请求映射到用户，并将每个用户与`OAuth2ClientContext`的单独实例关联起来。

在XML中，有一个带有id属性的`<client/>`元素—这是一个servlet过滤器的bean id，必须在`@Configuration`案例中映射到`DelegatingFilterProxy`(具有相同的名称)。


### 访问受保护的资源

一旦您提供了资源的所有配置，现在就可以访问这些资源了。访问这些资源的建议方法是使用Spring 3中引入的[RestTemplate][restTemplate]。Spring Security的OAuth提供了一个扩展的RestTemplate，它只需要提供 [`OAuth2ProtectedResourceDetails`][OAuth2ProtectedResourceDetails]的实例。要使用用户令牌(授权代码授权)，您应该考虑使用`@EnableOAuth2Client`配置(或XML等效的`<oauth:rest-template/>`)，它创建一些请求和会话范围的上下文对象，以便不同用户的请求在运行时不会发生冲突。

一般来说，web应用程序不应该使用密码授予，因此，如果您能够支持 `AuthorizationCodeResourceDetails`，请避免使用`ResourceOwnerPasswordResourceDetails`。如果您需要从Java客户端获得工作密码，那么使用相同的机制来配置`OAuth2RestTemplate`并将凭证添加到`AccessTokenRequest`(这是一个映射，并且是临时的)，而不是`ResourceOwnerPasswordResourceDetails` (它在所有访问令牌之间共享)。

### 在客户端持久化令牌

客户端不需要存留令牌，但对于用户来说，在每次重启客户端应用程序时都不需要批准新的令牌授权，这对用户来说是件好事。ClientTokenServices接口定义了为特定用户保存OAuth 2.0令牌所需的操作。这里提供了JDBC实现，但是如果您喜欢实现您自己的服务，以便在持久性数据库中存储存取令牌和相关的认证实例，您可以这样做。
如果您想使用这个特性，您需要为`OAuth2RestTemplate`提供一个特殊配置的`AccessTokenProvider`。

```java
@Bean
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public OAuth2RestOperations restTemplate() {
	OAuth2RestTemplate template = new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(accessTokenRequest));
	AccessTokenProviderChain provider = new AccessTokenProviderChain(Arrays.asList(new AuthorizationCodeAccessTokenProvider()));
	provider.setClientTokenServices(clientTokenServices());
	template.setAccessTokenProvider(provider);
	return template;
}
```

## 为外部OAuth2提供者的客户定制。

一些外部的OAuth2提供者（例如Facebook）并没有正确地实现该规范，或者他们只是被困在一个较旧版本的规范中，而不是Spring Security OAuth。要在客户端应用程序中使用这些提供者，您可能需要调整客户端基础结构的各个部分。

以Facebook为例，在tonr2应用程序中有一个Facebook特性（您需要更改配置以添加您自己的、有效的、客户id和secret——它们在Facebook的网站上很容易生成）。

Facebook令牌响应也包含一个不兼容的JSON条目，用于令牌的失效时间（它们使用`expires`而不是`expires_in`），因此，如果您想在应用程序中使用过期时间，您将不得不使用定制的`OAuth2SerializationService`来手动解码它。
