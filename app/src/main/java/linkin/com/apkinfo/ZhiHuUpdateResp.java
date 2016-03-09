package linkin.com.apkinfo;

import com.linkin.base.http.RestfulEntity;

/**
 * @author liangjunfeng
 * @since 2015/12/22 0022 11:20
 */
public class ZhiHuUpdateResp implements RestfulEntity {

    public int status;
    public String msg;
    public String url;
    public String latest;

    @Override
    public String toString() {
        return "ZhiHuUpdateBean : status = " + status + " | msg = " + msg + " | url = " + url + " | latest = " + latest;
    }
}
