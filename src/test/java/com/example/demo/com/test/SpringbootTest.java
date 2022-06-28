package com.example.demo.com.test;

import com.example.demo.com.mapper.CommodityMapper;
import com.example.demo.com.mapper.TestMapper;
import com.example.demo.com.pojo.sql.Commodity;
import com.example.demo.com.pojo.sql.UserBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTest {

    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    TestMapper testMapper;

    @Test
    public void Commodity() {
        List<Commodity> allCommodityByType = commodityMapper.findAllCommodity();
        System.out.println(allCommodityByType);

    }

    @Test
    public void testDelete() {

    }
    
    @Test
    public void selectFindById() {
        UserBean userBean = testMapper.findById("1");
        System.out.println(userBean);
    }

    @Test
    public void insertUser() {
        UserBean userBean = new UserBean();
        userBean.setPassword("666");
        userBean.setSno("2033010324");
        int i = testMapper.insertUser(userBean.getSno(), userBean.getPassword());
        System.out.println("添加了" + i + "条记录");
    }

    @Test
    public void deleteUserById() {
        int i = testMapper.deleteUserById(String.valueOf(2));
        if (i == 1) {
            System.out.println("删除成功");
        }
    }


//    @Test
//    public void addCommoDitysSql(){
//        Resource resource=new ClassPathResource("static/imags/nuts");
////        String[] c_names={};
//        String[] c_names={"Savanna","开心果","坚果大礼包","恰恰每日坚果","巴坦木","原香腰果","夹心海苔脆","山核桃仁"};
//        String[] c_prompts={"美国Savanna绿罐蜜烤混合坚果850G 开心果腰果扁桃仁碧根果 每日坚果零食","京西严选 美国加州甄选品质，原香大开心果 400克"
//                ,"【三只松鼠_坚果大礼包】2022年货零食坚果礼盒（以收到实物为准）",
//                "洽洽每日坚果 恰恰【浓郁蜜桃】零食大礼包30装 干果休闲零食早餐代餐核桃仁坚果年货礼品礼盒装",
//        "网易严选 颗颗橙黄无添加，原香巴旦木 560克","京西严选 原香腰果","新农哥海苔巴旦木芝麻夹心脆35gx3袋即食海苔脆儿童休闲零食",
//        "姚生记 临安山核桃仁218g特产小核桃仁 坚果零食新货核桃肉便携袋装"};
//
////        String[] c_prompts={"原产南美，中国各地及欧洲等地广为栽培。草莓营养价值高，含有多种营养物质 ，且有保健功效",
////        "如果川渝有味道，那么一半是火锅的麻辣，另一半就是柑橘的酸甜。","榴莲是热带著名水果之一，原产马来西亚。东南亚一些国家种植较多，" +
////                " 其中以泰国最多。中国广东﹑海南也有种植。榴莲在泰国最负有盛名，被誉为“水果之王”。它的气味浓烈、爱之者赞其香，厌之者怨其臭"
////        ,"杧果有热带果王之称，味香甜，汁多可口，营养价值高，含有多种维生素，尤以维生素钾最丰富","柠檬富含维生素C、糖类、钙、磷、铁、维生素B1、" +
////                "维生素B2、烟酸、奎宁酸、柠檬酸、苹果酸、橙皮苷、柚皮苷、香豆精、高量钾元素和低量钠元素等，对人体十分有益",
////        "苹果是人们常吃的水果，美称它是“水果之王\"。无论从它的口感还是营养价值都是名列前茅，深受广大群众的喜爱，深受欢迎",
////        "葡萄是世界最古老的果树树种之一，别称蒲陶、草龙珠、菩提子等，为葡萄科葡萄属木质藤本植物的果实，生食或制葡萄干并可" +
////                "酿酒，酿酒后的酒脚可提酒食酸，根和藤药用具有止呕、安胎等功效，葡萄的营养价值很高，含有b族维生素，维生素C，维" +
////                "生素p，钙，磷，钾，铁及人体所需的多种氨基酸","蕉不落叶，一叶舒则一叶焦，故谓之蕉"};
//        String property = System.getProperty("user.dir");
//        String s="\\target\\classes\\static";
//        try {
//            System.out.println("property:"+property);
//            String path = resource.getFile().getPath();
//            List<File> allFile = FileUtil.getAllFile(path);
//            for (int i = 0; i < allFile.size(); i++) {
//                File file = allFile.get(i);
//                String path1 = file.getPath();
//                System.out.println(path1);
//                String replace = path1.replace(property, "");
//                String replace1 = replace.replace(s, "");
//                String c_uri = replace1.replace("\\", "/");
//                String c_name = c_names[i];
//                String c_advertisement = c_prompts[i];
//                double c_price=12.3;
//                String c_type="坚果";
//                testMapper.addCommoditys(c_name,c_uri,c_advertisement, String.valueOf(c_price),c_type);
//
//
//            }
//
//            System.out.println("allFile:"+allFile);
//            System.out.println(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


}
