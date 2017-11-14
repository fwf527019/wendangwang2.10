package hb.xnwdw.com.wendangwang.netdata.entity;

/**
 * Created by Administrator on 2017/6/23.
 */

public class WXdata {
    /**
     * access_token : is7-YthW4tsQ1QMCdlaUuC1-2k5v8pH9o_bXLpE6Zcidlym6WkUYh_-YlOmvbGSpBqM-Of20AUYJHmIbFLThu9GFiwnvkjG-Uq55piUcCSw
     * expires_in : 7200
     * refresh_token : -DD4Ss9EnGEZNH-sqOsZDVf-zoFsBQKRlDRg7BhCJ3_AbiZxBvyRluiCx_jrW9tFuUg6NamzVGDVPsk2zx4H2jeINdzeS8AEd0owDiffcgg
     * openid : oLHrX0xGxjNxfqFMvxcLbsS3oDUk
     * scope : snsapi_userinfo
     * unionid : olNKrs7knqGQIn977UUGe8ezm8Pk
     */
    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
