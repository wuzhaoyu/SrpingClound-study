package entity;

/**
 * 类功能说明: 状态码
 * 类修改者	创建日期2020/2/9
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
public class StatusCode {
    public static final int OK=20000;//成功
    public static final int ERROR =20001;//失败
    public static final int LOGINERROR =20002; // 用户名或密码错误
    public static final int ACCESSERROR =20003; // 权限不足
    public static final int REMOTEERROR =20004;// 远程调用失败
    public static final int REPERROR =20005;//重复操作、

    public static final int MISTAKE_TOKEN =50008;//非法的token
    public static final int OTHER_LOGIN =50012;//其他客户端登录了
    public static final int EXPIRE =50014;//过期了
}
