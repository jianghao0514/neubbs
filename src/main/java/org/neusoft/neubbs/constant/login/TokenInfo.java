package org.neusoft.neubbs.constant.login;

/**
 *  Token常量
 */
public interface TokenInfo {
    String JWT = "JWT";
    String HS256 = "HS256";
    String RS256 = "RS256";

    String HEADER = "Header";       //头部
    String HEADER_TYP = "typ";      //类型
    String HEADER_ALG = "alg";      //加密算法

    String PAYLOAD = "Payload";     //载荷
    String PLAYLOAD_ISS = "iss";    //签发者（JWT签发）
    String PLAYLOAD_SUB = "sub";    //面向用户（JWT所面向）
    String PLAYLOAD_AUD = "aud";    //接收者（接收JWT）
    String PLAYLOAD_IAT = "iat";    //签发时间
    String PLAYLOAD_EXP = "exp";    //过期时间

    String SIGNATURE = "Signature"; //签名

    String SET_ISSUER = "neubbs";
    String SET_SUBJECT =  "www.neubbs.com";
    String SET_AUDIENCE = "user@neubbs.com";
    String SET_ISSUEDAT = "";
    String SET_EXPIRESAT = "";

    String CLAIM_USERNAME = "username";

    String SECRET_KEY = "this neubbs is best";
}
