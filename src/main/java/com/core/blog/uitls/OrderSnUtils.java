package com.core.blog.uitls;

/**
 * 包名：com.bfdl.order.util.
 * 版权：Copyright bfdl. All Rights Reserved.
 * 描述详情：
 * 创建者： sage.
 * 创建时间：2018/7/16-10:05.
 * <p>
 * 修改者：sage.
 * 修改时间：2018/7/16-10:05.
 * 修改原因：
 * 修改内容：
 */
public class OrderSnUtils {
    //慧购项目
    private static final String HUIGOU = "HG";
    //囿范项目
    private static  final String YOUFAN = "YF";
    //核销专用
    private static  final String HEXIAO = "HX";
    /**
     * 该方法功能详细描述：source 订单来源,1:慧购项目，2：囿范项目
     * @param source, memberId
     * @return java.lang.String
     * 创建人：sage
     * 创建时间：2018/7/16-10:49
     * 修改人：sage
     * 修改时间：2018/7/16-10:49
     * 修改内容：
     */
    public static String createOrderSnBy(int source,Integer memberId){

        String orderSn = "";
        if (null != memberId ){
            if (source ==1) {
                orderSn = orderSn.concat(HUIGOU).concat(memberId.toString()).concat(System.currentTimeMillis() + "");
            }else if (source == 2){
                orderSn = orderSn.concat(YOUFAN).concat(memberId.toString()).concat(System.currentTimeMillis() + "");
            }else if(source == 3){
                orderSn = orderSn.concat(HEXIAO).concat(memberId.toString()).concat(System.currentTimeMillis() + "");
            }
        }
        return orderSn;
    }
    /**
     * 该方法功能详细描述：生成核销码
     * @param
     * @return java.lang.String
     * 创建人：sage
     * 创建时间：2018/7/18-14:55
     * 修改人：sage
     * 修改时间：2018/7/18-14:55
     * 修改内容：
     */
    public static String QRCode(){

        String QRcode = System.currentTimeMillis()+"";
        QRcode = QRcode.substring(0,QRcode.length()-1);

        return QRcode;
    }
    

}
