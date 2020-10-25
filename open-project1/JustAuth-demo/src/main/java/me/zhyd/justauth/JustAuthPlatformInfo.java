package me.zhyd.justauth;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0.0
 * @date 2020/6/24 13:02
 * @since 1.0.0
 */
public enum JustAuthPlatformInfo {

    /**
     * 平台
     */
    GITEE("Gitee", "https://docs.justauth.whnb.wang/#/oauth/gitee", "", "v1.0.1"),
    BAIDU("百度", "", "", "v1.0.1"),
    CODING("coding", "https://docs.justauth.whnb.wang/#/oauth/coding", "", "v1.0.1"),
    CSDN("CSDN", "", "", "v1.0.1"),
    DINGTALK("钉钉", "", "", "v1.0.1"),
    GITHUB("Github", "https://docs.justauth.whnb.wang/#/oauth/github", "", "v1.0.1"),
    OSCHINA("开源中国", "", "", "v1.0.1"),
    ALIPAY("支付宝", "https://docs.justauth.whnb.wang/#/oauth/alipay", "", "v1.0.1"),
    WEIBO("微博", "https://docs.justauth.whnb.wang/#/oauth/weibo", "", "v1.0.1"),

    DOUYIN("抖音", "", "", "v1.4.0"),
    ELEME("饿了么", "", "", "v1.12.0"),
    FACEBOOK("Facebook", "", "", "v1.3.0"),
//    FEISHU("飞书", "", "", "1.14.0"),
    GITLAB("Gitlab", "", "", "v1.11.0"),
    GOOGLE("Google", "", "", "v1.3.0"),
    HUAWEI("华为", "", "", "v1.10.0"),
    JD("京东", "", "", "v1.15.1"),
    KUJIALE("酷家乐", "https://docs.justauth.whnb.wang/#/oauth/kujiale", "", "v1.11.0"),
    LINKEDIN("领英", "", "", "v1.4.0"),
    MEITUAN("美团", "", "", "v1.12.0"),
    MICROSOFT("微软", "", "", "v1.5.0"),
    MI("小米", "", "", "v1.5.0"),
    PINTEREST("Pinterest", "", "", "v1.9.0"),
    QQ("QQ", "https://docs.justauth.whnb.wang/#/oauth/qq", "", "v1.1.0"),
    RENREN("人人", "", "", "v1.9.0"),
    STACK_OVERFLOW("Stack Overflow", "", "", "v1.9.0"),
    TAOBAO("淘宝", "", "", "v1.2.0"),
    TEAMBITION("Teambition", "", "", "v1.9.0"),
    WECHAT_ENTERPRISE("微信企业版", "", "", "v1.10.0"),
    WECHAT_MP("微信公众平台", "", "", "v1.14.0"),
    WECHAT_OPEN("微信开放平台", "https://docs.justauth.whnb.wang/#/oauth/wechat_open", "", "v1.1.0"),
    TOUTIAO("今日头条", "", "", "v1.6.0-beta"),
    TWITTER("推特", "https://docs.justauth.whnb.wang/#/oauth/twitter", "", "v1.13.0"),
    ALIYUN("阿里云", "https://docs.justauth.whnb.wang/#/oauth/aliyun", "", "v1.15.5"),
    MYGITLAB("自定义的Gitlab", "", "", "-"),
    ;

    // 平台名
    private String name;
    // 帮助文档
    private String readme;
    // 官网api文档
    private String apiDoc;
    // 集成该平台的 版本
    private String since;

    JustAuthPlatformInfo(String name, String readme, String apiDoc, String since) {
        this.name = name;
        this.readme = readme;
        this.apiDoc = apiDoc;
        this.since = since;
    }

    public static List<Map<String, String>> getPlatformInfos() {
        List<Map<String, String>> list = new LinkedList<>();
        Map<String, String> map = null;
        JustAuthPlatformInfo[] justAuthPlatformInfos = JustAuthPlatformInfo.values();
        for (JustAuthPlatformInfo justAuthPlatformInfo : justAuthPlatformInfos) {
            map = new HashMap<>();
            map.put("name", justAuthPlatformInfo.getName());
            map.put("readme", justAuthPlatformInfo.getReadme());
            map.put("apiDoc", justAuthPlatformInfo.getApiDoc());
            map.put("since", justAuthPlatformInfo.getSince());
            map.put("enname", justAuthPlatformInfo.name().toLowerCase());
            list.add(map);
        }
        return list;
    }

    public String getName() {
        return name;
    }

    public String getReadme() {
        return readme;
    }

    public String getApiDoc() {
        return apiDoc;
    }

    public String getSince() {
        return since;
    }
}
