import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class  Tool{
    public static void main(String[] args) {
        // generateMapping("com.pharmacodia.db", ChiCTRBean.class);
       // generateMapping("com.pharmacodia.db", String.class,null );
        StringBuffer stringBuffer = new StringBuffer();
        String s = "被伦理委员会叫停（剂量爬坡期间出现输液相关超敏反应，原因待查。后续经查明过敏原、修订方案后，如伦理同意再次启动此试验，将与CDE沟通交流后再行启动）";

        System.out.println(s.contains("被伦理委员会叫停"));
    }

    /**
     * 仅处理两层对象关系
     *
     * @param
     * @param cls
     */
    private static void generateMapping(String prefix, Class cls, String root) {
        for (Field field : cls.getDeclaredFields()) {
            Class<?> type = field.getType();
            String rootName = field.getName();

                // 集合处理
                if (type.getName().startsWith("java.util.List")) {
                    Type genericType = field.getGenericType();
                    if (null == genericType) {
                        continue;
                    }
                    if (genericType instanceof ParameterizedType) {
                        ParameterizedType pt = (ParameterizedType) genericType;
                        // 得到泛型里的class类型对象
                        Class<?> actualTypeArgument = (Class<?>) pt.getActualTypeArguments()[0];
                        if(root!=null){
                            System.err.println(pfs(root+"_"+rootName, root+"."+rootName));
                        }else {
                            System.err.println(pfs(rootName, rootName));

                        }
                        if(!actualTypeArgument.getName().startsWith("java.lang")){
                            generateMapping(prefix,actualTypeArgument,rootName);
                        }
                    }
                }
           else if(type.getName().startsWith(prefix)) {
                    if(root!=null){
                        System.err.println(pfs(root+"_"+rootName, root+"."+rootName));
                        Arrays.asList(type.getDeclaredFields()).forEach(x -> System.err.println(pfs(root+"_"+rootName + "_" + x.getName(), root+"."+rootName + "." + x.getName())));
                    }else {
                        System.err.println(pfs(rootName, rootName));
                        Arrays.asList(type.getDeclaredFields()).forEach(x -> System.err.println(pfs(rootName + "_" + x.getName(), rootName + "." + x.getName())));
                    }
            } else {
                if(root!=null){
                    System.err.println(pfs(root+"_"+rootName, root+"."+rootName));
                }else {
                    System.err.println(pfs(rootName, rootName));

                }
            }
        }
    }

    private static String pfs(String s1, String s2) {
        String pfs = "public static final String {$1} = \"{$2}\";";
        return pfs.replace("{$1}", s1.toUpperCase()).replace("{$2}", s2);
    }

}