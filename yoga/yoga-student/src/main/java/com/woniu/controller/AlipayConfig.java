package com.woniu.controller;

import com.alipay.easysdk.kernel.Config;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016103100781577";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key ="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCOgM6du2IsO+PHNeihjR5qtgF46hxewgdz1jE8yITeN+WSE61Wy2+BNPddbKkZfTxIp/mN8Zf+EUckIZsAlhdqbxR25lmqUqW+NlgY5pJcEzpn0G82dYsQ7y5n4L8boxRyMDOvNIAdhtSC8hJQDAsb2XZn7m48ZVz22AICySpJdyFIHmHKV/qmMrmZo1r1s4hfv2CRmxUVOn7Muu5cy8sN+dzq4rnQbPJ5brCviyvL8AuEw6Kxuvy02RIpDm1KrOzrhwWrXf+ujOFZjdnHs2cKIp+P4KT4NCyDW/xU2TDM3TkCw027896VYR4jVtJYwlPr3Z6+dqP9BVR7fckB7SKNAgMBAAECggEAbFzuwWs44EI6l8YavJ6/Po5g2D3OjRAm8qMylfI1d3YyEL791T6l8C8C2/xBKqzdJrL1RkctOXqSCpuzbuKzs6/8c93+2VI3z0kr/ON48KN+imsp5tPDiIEZXMy/BpkgdyTIaLcXpVxeRaUm39AIsojPTYE2Op8p7lucS9oFWg8mtoEcVPcWwpGa7KkFGDp0YYPcKWJL/PEaLrpfXIdxo8V/hOSxZiwiUdEwsdXBYHYPajgXJd1qCyB8tyKQbBGfFv9N/wG/Wrc0bWy1xbmYyHMKU+/jb24JOIUoULO9KmYZaO3QpvKLn02hLD9yGXLjnAx5nlWuzoFFEsRpfUiFgQKBgQC/8M70m0u5Pnjo/nej4VNkoRVvi4pBczwdLej1RJvxA8O+rUiCJ4tCRawk9NztB092gnGpFWKSaQ6CKUikG75i7hKvVZ6E7foxuQSia7ftxkSpez1tDgcmBMwt8IvYrSnYkKI4U+dppi9goRykivcBWEY1J8RNdoEUBW2nY9WJyQKBgQC+EB1L/TPvFXPC2SmcPQAEClFvbMT/79eXo+8ELNfbTBx/yZH3VOWB8KJVzBzgXKkMWM6GvaEXggC56vD1TjxLcMuTjefCxLsgsjix8/7E2TTXiCoIeWy9T92n8CW9ZBYRHghgpadkOEZjUQibRpubJ8dhzqvcFSK8bGXqVhq0pQKBgARbo1dKb121XWls3aqK4ynVvnbZ5Dm4NBU1haSL+j5up3Mc3BiYbUED8/Si2qFHGTbHn20HX0WTvRCXf166gogcf7AbcY3zGUsnuEM3zuVHKyft9GcVenQSh9BDNemlhN216vj8brSnYUwAXHYf8ZvGNVHfpUVc+3FI7kRur9qBAoGAPr8LLP8WmiO25+jRt+bbjnnpkKOUFfqUbleMnRBA4wEpl3ZwXfsFGfTbYv/TuK5ekNjwtcL838nne3l4u0Ns3GsMilYa4U4ZiR3NYdJvlYUv0Mf/OHT91TH8t2iszO2x60zC70VBEfESK8+gy8JvtLVZOY0FZ88CfLo8mzR59JECgYADuKzGVMsQ7IcNuBscMkckPbTjyAmxzJ4V8JVkXn+p4qWoUg5D/QHKA3K0qQEh++y+yJ8joHfJmLEnFaPScHmBC7hzw4k3qReGk5eP82ZX+ji4oln2KJETZYE4dyVxXhhIzWC5kweYEXvEgu2DfgS3vEAwopU3HVFAabd8Ht0vUg==";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjoDOnbtiLDvjxzXooY0earYBeOocXsIHc9YxPMiE3jflkhOtVstvgTT3XWypGX08SKf5jfGX/hFHJCGbAJYXam8UduZZqlKlvjZYGOaSXBM6Z9BvNnWLEO8uZ+C/G6MUcjAzrzSAHYbUgvISUAwLG9l2Z+5uPGVc9tgCAskqSXchSB5hylf6pjK5maNa9bOIX79gkZsVFTp+zLruXMvLDfnc6uK50GzyeW6wr4sry/ALhMOisbr8tNkSKQ5tSqzs64cFq13/rozhWY3Zx7NnCiKfj+Ck+DQsg1v8VNkwzN05AsNNu/PelWEeI1bSWMJT692evnaj/QVUe33JAe0ijQIDAQAB";
    //public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlZgQWIFoRFLKufbCHgMflxfaibC0YjH3W+gS4fSiHr/CVDjWIgp7Kw8CI7FZKx7cnP0JPCPqXdR47hQ0cnyaFbj7neGhljNt+8A+Jh6EqNncaO5USTkFGhlPj3GUHXHEsloE5HoZqpFUj97u42H1TcnZqgZbxuEUVnOpJ9w9u3onzwRzTJEWZ6HsXHCbDA7d80a+/MOQWNAmzH0vrt07OG5DagATamEkNdvRjyNLd2t7lSvmgOgTgp/Ogq4JRWR2lhVnXVb4O6ugdM1PGQoV8IzP5MnU2u1ecwV4XMqruhTj7q8laK8/IrnXoUWAGqdf8OAHpjmWy3pr9RfiyBZw7wIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://330561d1p4.qicp.vip/pay";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "tcp://f3d5273747.qicp.vip/index.html";
   // 诊断域名：f3d5273747.qicp.vip 域名IP地址指向：106.91.210.53 转发服务器IP：103.46.128.45 域名与转发服务器IP指向不一致 连接转发服务器成功   映射：f3d5273747.qicp.vip:26772 局域网服务器：127.0.0.1:8080 本机内网IP：192.168.10.21 局域网服务器连接成功
    // 签名方式0
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "openapi.alipaydev.com";

    // 支付宝网关
    public static String log_path = "C:\\";

    public static  Config getOptions(){
        Config config = new Config();
        config.protocol = "https";
        config.appId = "2016103100781403";
        //2021000116668016
        config.signType = "RSA2";
        config.gatewayHost = "openapi.alipaydev.com";
        config.alipayPublicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgfRp+bMIy674dRlDnI+0coDN0UIhuCDpr2Tzv8DsciUR/pfVADGCZZUs2muVw40Tl6DBgVAI0RyGJJ+5heTMHOU4jlRKONh2jM4Bw3/x0Wyl4hCk8doDsRcg8OSBlyu860b3dv1Rs5XWnAcW3uArn9xGtGCfrTxVU572tmzPHAjqhjj5QPfuIEAJ7cELSXMxAaKTTIT+tQzZs/h1Dt44pQKtpoVbL5XHp8/knphCpfm9DnGAyV24Mz18kn4kL1meDJkdfCP84K2Dix6CmCsA94PbaEKkK4rLdgYLN9LA7/XXXkpdVXHS7MGj+cBUoQ1Kj7z9SuShyoeIYMi2ZthqLQIDAQAB";
        config.notifyUrl = "tcp://f3d5273747.qicp.vip/alipay-demo/notify_url";
        config.merchantPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDjFJ/BUZqyX+++f2lokV2F5l7Ob1y2pkb41LsH9eUmjX6wKauCo6Evazf2dtOAweMhrbTowlkoqFCc6fT9i+yBF9CjERiIvkjM9UHsvpN5RD3DKGF27y7QTHeQG/GADM/QvyUeCTjmjHoTP1NBtLAmP4Esglie3wkALwJdLDVuGQZbL2wingCMh0MKQ81kZQL3I/RUdCZ333EGM5ZzhHILBzlB76JVwdBbKCJaDwAfjiK+Nq2K2U2majLtoL0s4RWdFKn4o2CfWEWkGYjxKfuqumLj5e8CXqxzqndJZc2B4VPwCl+waBQR/g7R7l5YyKR+ptfTX4oTTS+g8XezPAnHAgMBAAECggEARBiaM7nJ/zW/ykuhC4eUGSRVR+ijT+6bF+GLWL7HggEqfZ9xXFK/cl3fwVwu3szF1QKnXqlxXTzkN7n9Cs+cE7ZVjaEBz3fokvX1WA5qYRIyx0RjvevKCRL1Q9TtXe35n+rCMbioRJLZxM3XpLuPm1it3vATrUjLBTATsGcieqobzpAxCR7sN8sBa5VknY37xkIwsqyYMA8EWZhuEELCDsHZDeCh+wqHOoBkdlWdjEKCnhpLCUXDA5c/zmJF1VNWKHQYYPRtxVh1g2k6A0q2bWJBYbjxZGYnoG6Q6eFYr0Q2zZ/hGkW98WEXGn7hoDPSsIzwXUBfu1FyvfEJXF/3IQKBgQD4gtuBMt5LU8VvqdxvpKnlnT7Tr8ab3dEckEAa7EXJU4Vf875ksOwInVItSKHAgav24WT+TtBCx4lssl4NByZQXnJlYcMDp1NS2xDRxNob0tUEjboVamgjJLPPTyeFZb1IFqcwY7MzRadK9ngedKnIvgbs0t2XmiMXD1zxgcfNNwKBgQDp7HCh20kaC/Hc4L0FyPi5LZS+n9dopcOA1RU2Fzk1xMgtvlms4+MniMyAOnh90UiIACI8SSRhW9HrZkIgCG6P5JTxy6Jc627nWaOaKE23Oh28LRvuQuwTfmApipS9rLRmRxwNpsiLaUNCRFjz2B4ktMn0OM80YDehE3p0iZ9v8QKBgQC3xoWvUL/lMskSH6pdgYcGv7djclzMjUgD+IT/o/SjwrkiUS1eZxvV7wmDZXAA3aQfCWXXTs9iYw80q98zgTMLQh0U7iv6OrY60rfoeMvWwZ44JNsU1UC3pH+4ck/GrYtBkUcIWOJ/qyXhIM2JsDqSsjyKzBGE+D604e6YxqHMFwKBgQDWIMgtcUTmYd7BOLvuyrvbDr1lDX4qXeqrSOKExBdrf7laHi+KUoTB0LiZ/GwFiAjj7XNpSe7SbDGpUM7g8bb7Yttwxt3WPvEjN2vKFnClc756XbR+kqX1Zi5yeFeVlL0CDr/AQ1a6Pt4Bj/z5IXDeWtuvAj2yDQ1/Fgx3Fu9qkQKBgFu0bRvWR43giuCRBDDnAWojODRPzN0pjdIQ8SNreFlPMU+p21JMkTUjsHLy9PmpqfZBQh3Bv2dGZIrBFSd8WNtK0TkQH8GZ4Yxu00VVSX8PrqnQhX1GE1xOf2yabH4FJNOy5IFo+YU7o2B6+3uhnSVuZwPD6lG1bDhaSQ/WBSXv";

        return config;
    }
}
