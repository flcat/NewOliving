package kicc.module;

public class KiccModule {
    public static native byte[]  	KiccGetVersion();
    public static native byte[]		KiccGetTRNO(String Rid, String sPath);
    public static native byte[]  	KiccClearShopInfo(String sPath);
    public static native byte[]  	KiccDownShopInfo(
            String Busino      , String Areano      ,
            String TID         , String Agentcd     ,
            String Telno       ,
            String Server_ip   , int Server_port   , String sPath);
    public static native byte[]  	KiccGetShopInfo(
            String Code , String sPath);

    public static native byte[]		KiccApproval(
            int ReqType			,String ReqMsg,
            int ReqMsgLen		,int ResType,
            String BmpFile		,String Emvdata,  String Server_ip 	,
            int Server_port		,int Secure			,
            String Rid			,String TID,
            String TRNO, String sPath);
    public static native byte[]		KiccApprovalBarcode(
            int ReqType			,String ReqMsg,
            int ReqMsgLen		,int ResType,
            String BmpFile		,String Emvdata,  String Server_ip 	,
            int Server_port		,int Secure			,
            String Rid			,String TID,
            String TRNO, String sPath);
    public static native byte[]		KiccApprovalByte(
            int ReqType			,byte[] ReqMsg,
            int ReqMsgLen		,int ResType,
            String BmpFile		,String Emvdata,  String Server_ip 	,
            int Server_port		,int Secure			,
            String Rid			,String TID,
            String TRNO, String sPath);
    public static native byte[]		KiccRollBack(
            String Server_ip 	,int Server_port,
            int Secure			,String Rid			, String sPath);
    public static native byte[] bb();
    public static native byte[]		KiccRollBackA(
            String Server_ip 	,int Server_port,
            int Secure			,String Rid			, String TRNO,  String sPath);
    public static native byte[] KiccMsrTpkDown(String ModelCode, String Firmware, String Serial, String AuthInfo, String Protocol, String Ip, int port, int debugflag);
    public static native byte[] KiccMsrPkDown(String ModelCode, String Firmware, String Serial, String Protocol, String Ip, int port, int debugflag);
    public static native byte[] KiccMsrSPkDown(String ModelCode, String Firmware, String Serial, String Protocol, String KTCNo, String AuthData, String Ip, int port, int debugflag);
    public static native byte[] KiccMsrSTMKDown(String ModelCode, String Firmware, String Serial, String Protocol, String KTCNo, String AuthData, String SPKVer, String Ip, int port, int debugflag);
    public static native byte[] KiccGetPosSeq(String uuid, String tid, String ip, int port, int secure, String rid, String trno, String posSeq, String sPath);
    static {
        System.loadLibrary("KiccCommLibrary");
    }
}
