import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeTest {
    static String json = "[{'id':'1','name':'中国','code':'110','parent':''}," +
            "{'id':'2','name':'北京','code':'110000','parent':'110'}," +
            "{'id':'3','name':'河北省','code':'130000','parent':'110'}," +
            "{'id':'4','name':'四川省','code':'510000','parent':'110'}," +
            "{'id':'5','name':'石家庄市','code':'130001','parent':'130000'}," +
            "{'id':'6','name':'唐山市','code':'130002','parent':'130000'}," +
            "{'id':'7','name':'邢台市','code':'130003','parent':'130000'}," +
            "{'id':'8','name':'成都市','code':'510001','parent':'510000'}," +
            "{'id':'9','name':'简阳市','code':'510002','parent':'510000'}," +
            "{'id':'10','name':'武侯区','code':'51000101','parent':'510001'}" +
            "{'id':'11','name':'金牛区','code':'51000102','parent':'510001'}]";


    @Test
    public void test() throws Exception{
        List<Goods> listStr = JSON.parseArray(json,Goods.class);

        //System.out.println(listStr.size());

        Map<Long, Goods> map =  new HashMap<>();

        for (Goods menu : listStr) {
            map.put(menu.getCode(), menu);
            //System.out.println(menu.getParent());
        }
        // 查询组织树
        List<Goods> menuTree = new ArrayList<>();
        for (Goods menu : listStr) {
            // 如果没有父级菜单
            if (menu.getParent() == null) {
                // 添加自己为父级菜单
                menuTree.add(menu);
            }else {
                // 如果有，获取父级菜单的parent
                Long parentId = menu.getParent();
                // 添加父级菜单
                Goods menu1 = map.get(parentId);
                // 将自己添加到父级菜单下
                menu1.getChildren().add(menu);
            }
        }

        String jsonString = JSON.toJSONString(menuTree);

        System.out.println(jsonString);

    }

  public static class Goods {
        private Long id;
        private String name;
        private Long code;
        private Long parent;

        // 用来保存当前部门的子部门，属性名要于elementUi组件中的属性一致，不能改
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private List<Goods> children = new ArrayList<>();

        public List<Goods> getChildren() {
            return children;
        }

        public void setChildren(List<Goods> children) {
            this.children = children;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getCode() {
            return code;
        }

        public void setCode(Long code) {
            this.code = code;
        }

        public Long getParent() {
            return parent;
        }

        public void setParent(Long parent) {
            this.parent = parent;
        }
    }
}
