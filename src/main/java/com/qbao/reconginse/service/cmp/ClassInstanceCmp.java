package com.qbao.reconginse.service.cmp;

/**
 *   把类封装成单列模式
     * @author 贾红平
     * $LastChangedDate$
     * $LastChangedRevision$
     * $LastChangedBy$
 */
public class ClassInstanceCmp {
    
    static Object obj=null;
    
    public static synchronized Object getInstanceObj(String clzName){
        if (obj==null) {
               try {
                   Class<?> onwClass=Class.forName("com.qbao.reconginse.service.impl.card."+clzName);
                   obj = onwClass.newInstance();
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
        return obj;
    }
   
}
