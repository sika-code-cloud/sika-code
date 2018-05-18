
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

*  `JdbcTokenStore`和JDBC版本是同一种东西，它使用关系数据库来存储令牌数据。如果您可以在服务器之间共享一个数据库，那么可以使用JDBC版本，如果只有一个服务器，则可以扩展相同服务器的实例，如果有多个组件，则可以使用授权和资源服务器。为了使用 `JdbcTokenStore` ，您需要将 "spring-jdbc" 配置到classpath中.

*  [JSON Web Token (JWT) version](`JwtTokenStore`) 将所有关于grant的数据编码到令牌本身(因此没有任何后端存储，这是一个重要的优势)。 一个缺点是，您不能很容易地撤销访问令牌，因此它们通常在短时间内被授予，而撤销则在刷新令牌中处理。 另一个缺点是，如果您在其中存储了大量用户凭证信息，则令牌可以变得相当大。 `JwtTokenStore`并不是真正的“存储”，因为它没有保存任何数据，但是它在`DefaultTokenServices`中扮演了转换betweeen令牌值和身份验证信息的角色。

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

The token endpoint is protected for you by default by Spring OAuth in the `@Configuration` support using HTTP Basic authentication of the client secret. This is not the case in XML (so it should be protected explicitly).

In XML the `<authorization-server/>` element has some attributes that can be used to change the default endpoint URLs in a similar way. The `/check_token` endpoint has to be explicitly enabled (with the `check-token-enabled` attribute).

## Customizing the UI

Most of the Authorization Server endpoints are used primarily by machines, but there are a couple of resource that need a UI and those are the GET for `/oauth/confirm_access` and the HTML response from `/oauth/error`. They  are provided using whitelabel implementations in the framework, so most real-world instances of the Authorization Server will want to provide their own so they can control the styling and content. All you need to do is provide a Spring MVC controller with `@RequestMappings` for those endpoints, and the framework defaults will take a lower priority in the dispatcher. In the `/oauth/confirm_access` endpoint you can expect an `AuthorizationRequest` bound to the session carrying all the data needed to seek approval from the user (the default implementation is `WhitelabelApprovalEndpoint` so look there for a starting point to copy). You can grab all the data from that request and render it however you like, and then all the user needs to do is POST back to `/oauth/authorize` with information about approving or denying the grant. The request parameters are passed directly to a `UserApprovalHandler` in the `AuthorizationEndpoint` so you can interpret the data more or less as you please. The default `UserApprovalHandler` depends on whether or not you have supplied an `ApprovalStore` in your `AuthorizationServerEndpointsConfigurer` (in which case it is an `ApprovalStoreUserApprovalHandler`) or not (in which case it is a `TokenStoreUserApprovalHandler`). The standard approval handlers accept the following:

* `TokenStoreUserApprovalHandler`: a simple yes/no decision via `user_oauth_approval` equals to "true" or "false".

* `ApprovalStoreUserApprovalHandler`: a set of `scope.*` parameter keys with "*" equal to the scopes being requested. The value of the parameter can be "true" or "approved" (if the user approved the grant) else the user is deemed to have rejected that scope. A grant is successful if at least one scope is approved.

> NOTE: don't forget to include CSRF protection in your form that you render for the user. Spring Security is expecting a request parameter called "_csrf" by default (and it provides the value in a request attribute). See the Spring Security user guide for more information on that, or look at the whitelabel implementation for guidance.

### Enforcing SSL

Plain HTTP is fine for testing but an Authorization Server should only be used over SSL in production. You can run the app in a secure container or behind a proxy and it should work fine if you set the proxy and the container up correctly (which is nothing to do with OAuth2). You might also want to secure the endpoints using Spring Security `requiresChannel()` constraints. For the `/authorize` endpoint is up to you to do that as part of your normal application security. For the `/token` endpoint there is a flag in the `AuthorizationServerSecurityConfigurer` that you can set using the `sslOnly()` method. In both cases the secure channel setting is optional but will cause Spring Security to redirect to what it thinks is a secure channel if it detects a request on an insecure channel.

## Customizing the Error Handling

Error handling in an Authorization Server uses standard Spring MVC features, namely `@ExceptionHandler` methods in the endpoints themselves. Users can also provide a `WebResponseExceptionTranslator` to the endpoints themselves which is the best way to change the content of the responses as opposed to the way they are rendered. The rendering of exceptions delegates to `HttpMesssageConverters` (which can be added to the MVC configuration) in the case of token endpoint and to the OAuth error view (`/oauth/error`) in the case of the authorization endpoint. The whitelabel error endpoint is provided for HTML responses, but users probably need to provide a custom implementation (e.g. just add a `@Controller` with `@RequestMapping("/oauth/error")`).

## Mapping User Roles to Scopes

It is sometimes useful to limit the scope of tokens not only by the scopes assigned to the client, but also according to the user's own permissions. If you use a `DefaultOAuth2RequestFactory` in your `AuthorizationEndpoint` you can set a flag `checkUserScopes=true` to restrict permitted scopes to only those that match the user's roles. You can also inject an `OAuth2RequestFactory` into the `TokenEndpoint` but that only works (i.e. with password grants) if you also install a `TokenEndpointAuthenticationFilter` - you just need to add that filter after the HTTP `BasicAuthenticationFilter`. Of course, you can also implement your own rules for mapping scopes to roles and install your own version of the `OAuth2RequestFactory`. The `AuthorizationServerEndpointsConfigurer` allows you to inject a custom `OAuth2RequestFactory` so you can use that feature to set up a factory if you use `@EnableAuthorizationServer`.

## Resource Server Configuration

A Resource Server (can be the same as the Authorization Server or a separate application) serves resources that are protected by the OAuth2 token. Spring OAuth provides a Spring Security authentication filter that implements this protection. You can switch it on with `@EnableResourceServer` on an `@Configuration` class, and configure it (as necessary) using a `ResourceServerConfigurer`. The following features can be configured:

* `tokenServices`: the bean that defines the token services (instance of `ResourceServerTokenServices`).
* `resourceId`: the id for the resource (optional, but recommended and will be validated by the auth server if present).
* other extension points for the resources server (e.g. `tokenExtractor` for extracting the tokens from incoming requests)
* request matchers for protected resources (defaults to all)
* access rules for protected resources (defaults to plain "authenticated")
* other customizations for the protected resources permitted by the `HttpSecurity` configurer in Spring Security

The `@EnableResourceServer` annotation adds a filter of type `OAuth2AuthenticationProcessingFilter` automatically to the Spring Security filter chain.

In XML there is a `<resource-server/>` element with an `id` attribute - this is the bean id for a servlet `Filter` that can then be added manually to the standard Spring Security chain.

Your `ResourceServerTokenServices` is the other half of a contract with the Authorization Server. If the Resource Server and Authorization Server are in the same application and you use `DefaultTokenServices` then you don't have to think too hard about this because it implements all the necessary interfaces so it is automatically consistent. If your Resource Server is a separate application then you have to make sure you match the capabilities of the Authorization Server and provide a `ResourceServerTokenServices` that knows how to decode the tokens correctly. As with the Authorization Server, you can often use the `DefaultTokenServices` and the choices are mostly expressed through the `TokenStore` (backend storage or local encoding). An alternative is the `RemoteTokenServices` which is a Spring OAuth features (not part of the spec) allowing Resource Servers to decode tokens through an HTTP resource on the Authorization Server (`/oauth/check_token`). `RemoteTokenServices` are convenient if there is not a huge volume of traffic in the Resource Servers (every request has to be verified with the Authorization Server), or if you can afford to cache the results. To use the `/oauth/check_token` endpoint you need to expose it by changing its access rule (default is "denyAll()") in the `AuthorizationServerSecurityConfigurer`, e.g.

```
@Override
public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
	oauthServer.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')")
	.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
}

```

In this example we are configuring both the `/oauth/check_token` endpoint and the `/oauth/token_key` endpoint (so trusted resources can obtain the public key for JWT verification). These two endpoints are protected by HTTP Basic authentication using client credentials.

### Configuring An OAuth-Aware Expression Handler

You may want to take advantage of Spring Security's [expression-based access control][expressions]. An expression handler will be registered by default in the `@EnableResourceServer` setup. The expressions include _#oauth2.clientHasRole_, _#oauth2.clientHasAnyRole_, and _#oath2.denyClient_ which can be used to provide access based on the role of the oauth client (see `OAuth2SecurityExpressionMethods` for a comprehensive list). In XML you can register a oauth-aware expression handler with the `expression-handler` element of the regular `<http/>` security configuration.

## OAuth 2.0 Client

The OAuth 2.0 client mechanism is responsible for access the OAuth 2.0 protected resources of other servers. The configuration involves establishing the relevant protected resources to which users might have access. The client may also need to be supplied with mechanisms for storing authorization codes and access tokens for users.

### Protected Resource Configuration

Protected resources (or "remote resources") can be defined using bean definitions of type [`OAuth2ProtectedResourceDetails`][OAuth2ProtectedResourceDetails]. A protected resource has the following properties:

* `id`: The id of the resource. The id is only used by the client to lookup the resource; it's never used in the OAuth protocol. It's also used as the id of the bean.
* `clientId`: The OAuth client id. This is the id by which the OAuth provider identifies your client.
* `clientSecret`: The secret associated with the resource. By default, no secret is empty.
* `accessTokenUri`: The URI of the provider OAuth endpoint that provides the access token.
* `scope`: Comma-separted list of strings specifying the scope of the access to the resource. By default, no scope will be specified.
* `clientAuthenticationScheme`: The scheme used by your client to authenticate to the access token endpoint. Suggested values: "http\_basic" and "form". Default: "http\_basic". See section 2.1 of the OAuth 2 spec.

Different grant types have different concrete implementations  of `OAuth2ProtectedResourceDetails` (e.g. `ClientCredentialsResource` for "client_credentials" grant type).  For grant types that require user authorization there is a further property:

* `userAuthorizationUri`: The uri to which the user will be redirected if the user is ever needed to authorize access to the resource. Note that this is not always required, depending on which OAuth 2 profiles are supported.

In XML there is a `<resource/>` element that can be used to create a bean of type `OAuth2ProtectedResourceDetails`. It has attributes matching all the properties above.


### Client Configuration

For the OAuth 2.0 client, configuration is simplified using `@EnableOAuth2Client`. This does 2 things:

* Creates a filter bean (with ID `oauth2ClientContextFilter`) to store the current
  request and context. In the case of needing to authenticate during a
  request it manages the redirection to and from the OAuth
  authentication uri.

* Creates a bean of type `AccessTokenRequest` in request scope. This
  can be used by authorization code (or implicit) grant clients to keep
  state related to individual users from colliding.

The filter has to be wired into the application (e.g. using a Servlet
initializer or `web.xml` configuration for a `DelegatingFilterProxy`
with the same name).

The `AccessTokenRequest` can be used in an
`OAuth2RestTemplate` like this:

```
@Autowired
private OAuth2ClientContext oauth2Context;

@Bean
public OAuth2RestTemplate sparklrRestTemplate() {
	return new OAuth2RestTemplate(sparklr(), oauth2Context);
}
```

The OAuth2ClientContext is placed (for you) in session scope to keep
the state for different users separate. Without that you would have to
manage the equivalent data structure yourself on the server, mapping
incoming requests to users, and associating each user with a separate
instance of the `OAuth2ClientContext`.

In XML there is a `<client/>` element with an `id` attribute - this is the bean id for a servlet `Filter` that must be mapped as in the `@Configuration` case to a `DelegatingFilterProxy` (with the same name).


### Accessing Protected Resources

Once you've supplied all the configuration for the resources, you can now access those resources. The suggested method for accessing those resources is by using [the `RestTemplate` introduced in Spring 3][restTemplate]. OAuth for Spring Security has provided [an extension of RestTemplate][OAuth2RestTemplate] that only needs to be supplied an instance of [`OAuth2ProtectedResourceDetails`][OAuth2ProtectedResourceDetails].  To use it with user-tokens (authorization code grants) you should consider using the `@EnableOAuth2Client` configuration (or the XML equivalent `<oauth:rest-template/>`) which creates some request and session scoped context objects so that requests for different users do not collide at runtime.

As a general rule, a web application should not use password grants, so avoid using `ResourceOwnerPasswordResourceDetails` if you can in favour of `AuthorizationCodeResourceDetails`. If you desparately need password grants to work from a Java client, then use the same mechanism to configure your `OAuth2RestTemplate` and add the credentials to the `AccessTokenRequest` (which is a `Map` and is ephemeral) not the `ResourceOwnerPasswordResourceDetails` (which is shared between all access tokens).

### Persisting Tokens in a Client

A client does not *need* to persist tokens, but it can be nice for users to not be required to approve a new token grant every time the client app is restarted. The [`ClientTokenServices`](/spring-security-oauth2/src/main/java/org/springframework/security/oauth2/client/token/ClientTokenServices.java) interface defines the operations that are necessary to persist OAuth 2.0 tokens for specific users. There is a JDBC implementation provided, but you can if you prefer implement your own service for storing the access tokens and associated authentication instances in a persistent database.
If you want to use this feature you need provide a specially configured `AccessTokenProvider` to the `OAuth2RestTemplate` e.g.

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

## Customizations for Clients of External OAuth2 Providers

Some external OAuth2 providers (e.g. [Facebook][Facebook]) do not quite implement the specification correctly, or else they are just stuck on an older version of the spec than Spring Security OAuth. To use those providers in your client application you might need to adapt various parts of the client-side infrastructure.

To use Facebook as an example, there is a Facebook feature in the `tonr2` application (you need to change the configuration to add your own, valid, client id and secret - they are easy to generate on the Facebook website).

Facebook token responses also contain a non-compliant JSON entry for the expiry time of the token (they use `expires` instead of `expires_in`), so if you want to use the expiry time in your application you will have to decode it manually using a custom `OAuth2SerializationService`.

[AuthorizationEndpoint]: http://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/endpoint/AuthorizationEndpoint.html	"AuthorizationEndpoint"
[TokenEndpoint]: http://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/endpoint/TokenEndpoint.html	"TokenEndpoint"
[DefaultTokenServices]: http://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/token/DefaultTokenServices.html	"DefaultTokenServices"
[InMemoryTokenStore]: http://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/token/store/InMemoryTokenStore.html	"InMemoryTokenStore"
[JdbcTokenStore]: http://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/token/store/JdbcTokenStore.html	"JdbcTokenStore"
[ClientDetailsService]: http://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/ClientDetailsService.html	"ClientDetailsService"
[ClientDetails]: http://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/ClientDetails.html	"ClientDetails"
[InMemoryClientDetailsService]: http://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/InMemoryClientDetailsService.html	"InMemoryClientDetailsService"
[BaseClientDetails]: http://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/BaseClientDetails.html	"BaseClientDetails"
[AuthorizationServerTokenServices]: http://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/token/AuthorizationServerTokenServices.html	"AuthorizationServerTokenServices"
[OAuth2AuthenticationProcessingFilter]: http://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/authentication/OAuth2AuthenticationProcessingFilter.html	"OAuth2AuthenticationProcessingFilter"
[oauth2.xsd]: http://www.springframework.org/schema/security/spring-security-oauth2.xsd	"oauth2.xsd"
[expressions]: http://docs.spring.io/spring-security/site/docs/3.2.5.RELEASE/reference/htmlsingle/#el-access	"Expression Access Control"

[AccessTokenProviderChain]: /spring-security-oauth2/src/main/java/org/springframework/security/oauth2/client/token/AccessTokenProviderChain.java
[OAuth2RestTemplate]: /spring-security-oauth2/src/main/java/org/springframework/security/oauth2/client/OAuth2RestTemplate.java
[OAuth2ProtectedResourceDetails]: /spring-security-oauth2/src/main/java/org/springframework/security/oauth2/client/resource/OAuth2ProtectedResourceDetails.java
[restTemplate]: http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html	"RestTemplate"
[Facebook]: http://developers.facebook.com/docs/authentication	"Facebook"
