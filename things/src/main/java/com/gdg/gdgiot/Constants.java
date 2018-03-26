package com.gdg.gdgiot;

/**
 * Created by Kevin_Chiu on 2018/03/19.
 */


public class Constants {
    public static final int NULL_INT =0;

    /*
    　中央音Do　為256.00Hz　　　　　So　 為383.57Hz
　　　　    Do#為271.22Hz　　　　　So#  為406.37Hz
　　　　    Re　為287.35Hz　　　　　La　  為430.54Hz
　　　　    Re#為304.44Hz　　　　　La#   為456.14Hz
　　　　    Mi　為322.54Hz　　　　　Si　   為483.26Hz
　　　　    Fa　為341.72Hz　　　    高音Do為512.00Hz
　　　　    Fa#為362.04Hz　　　     (其中#表示升半音)
     */
    public static final double DO_FREQ_DUB = 256.00;
    public static final double DO_FREQ_UP_HALF_DUB = 271.22;
    public static final double RE_FREQ_DUB = 287.35;
    public static final double RE_FREQ_UP_HALF_DUB = 304.44;
    public static final double MI_FREQ_DUB = 322.54;
    public static final double FA_FREQ_DUB = 341.72;
    public static final double FA_FREQ_UP_HALF_DUB = 362.04;
    public static final double SO_FREQ_DUB = 383.57;
    public static final double SO_FREQ_UP_HALF_DUB = 406.37;
    public static final double LA_FREQ_DUB = 430.54;
    public static final double LA_FREQ_UP_HALF_DUB = 456.14;
    public static final double SI_FREQ_DUB = 483.26;

    /*
    低音  Do  Re  Mi  Fa  So  La  Si
    頻率  262 294 330 349 392 440 494

    中音  Do  Re  Mi  Fa  So  La  Si
    頻率  523 587 659 698 784 880 988

    高音  Do   Re   Mi    Fa    So    La    Si
    頻率  1046 1175 1318  1397  1568  1760  1976
     */

    public static final int DO_FREQ_INT = 523;
    public static final int DO_FREQ_UP_HALF_INT = 554;
    public static final int RE_FREQ_INT = 587;
    public static final int RE_FREQ_UP_HALF_INT = 622;
    public static final int MI_FREQ_INT = 659;
    public static final int FA_FREQ_INT = 698;
    public static final int FA_FREQ_UP_HALF_INT = 740;
    public static final int SO_FREQ_INT = 784;
    public static final int SO_FREQ_UP_HALF_INT = 831;
    public static final int LA_FREQ_INT = 880;
    public static final int LA_FREQ_UP_HALF_INT = 932;
    public static final int SI_FREQ_INT = 988;

    /*
    音名	 C	   C#	 D	   D#	 E	   F	 F#	   G	 G#	   A	 A#	   B
    0	 16	   17	 18	   19	 21	   22	 23	   25	 26	   28	 29	   31
    1	 33	   35	 37	   39	 41	   44	 46	   49	 52	   55	 58	   62
    2	 65	   69	 73	   78 	 82    87 	 93    98 	 104   110	 117   123
    3	 131   139   147   156	 165   175	 185   196	 208   220	 233   247
    4	 262   277	 294   311	 330   349	 370   392	 415   440	 466   493
    5	 523   554	 587   622	 659   698	 740   784	 831   880	 932   988
    6	 1046  1109	 1175  1245  1319  1397	 1480  1568  1661  1760	 1864  1976
    7	 2093  2217	 2349  2489	 2637  2794	 2960  3136	 3322  3520	 3729  3951
    8	 4186  4435	 4699  4978	 5274  5588	 5920  6272	 6645  7040	 7459  7902
    */
}
