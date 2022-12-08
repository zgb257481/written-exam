import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class Json {

        static String json = "[{'name':'张三','serial':0001}," +
                      "{'name':'李四','serial':0002}," +
                      "{'name':'王五','serial':0003}," +
                "{'name':'王五2','serial':0003}," +
                "{'name':'赵四','serial':0004}," +
                "{'name':'小明','serial':005}," +
                "{'name':'小张','serial':006}," +
                "{'name':'小李','serial':006}," +
                "{'name':'小李2','serial':006}," +
                "{'name':'赵四2','serial':0004}]";


        @Test
        public void test() throws Exception{
                JSONArray array = JSONArray.parseArray(json);
                JSONArray arrayTemp = new JSONArray();

                int num = 0;
                for (int i = 0; i < array.size(); i++) {
                        //第一个数组中数据放入进行对比
                        if (num == 0) {
                                arrayTemp.add(array.get(i));
                        } else {
                                int numJ = 0;
                                for (int j = 0; j < arrayTemp.size(); j++) {
                                        //获取数组中的第i条数据
                                        JSONObject newJsonObjectI = (JSONObject) array.get(i);
                                        //获取新数组第J条数据
                                        JSONObject newJsonObjectJ = (JSONObject) arrayTemp.get(j);
                                        //获取serial
                                        String index_idI = newJsonObjectI.get("serial").toString();
                                        //获取serial
                                        String index_idJ = newJsonObjectJ.get("serial").toString();
                                        //比对serial 如果相同，删除新数组中的第J条数据 并把数组中的数据放到新数组中
                                        if (index_idI.equals(index_idJ)) {
                                                arrayTemp.remove(j);
                                                arrayTemp.add(newJsonObjectI);
                                                break;
                                        }
                                        numJ++;
                                }
                                if (numJ - 1 == arrayTemp.size() - 1) {
                                        arrayTemp.add(array.get(i));
                                }
                        }
                        num++;
                }
                System.out.println(arrayTemp);
        }
}
