package com.liang.iotmall.product.web;

import java.lang.reflect.Field;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Mr.Liang
 * @date: 2021/9/12 20:03
 * @Version: 1.0
 */
public class UserService {


    static class UserInfo {
        public UserInfo() {
        }

        public UserInfo(Integer id, String userId, String userName, String img, Integer gender, LocalDate birthday, Integer height, String hometown) {
            this.id = id;
            this.userId = userId;
            this.userName = userName;
            this.img = img;
            this.gender = gender;
            this.birthday = birthday;
            this.height = height;
            this.hometown = hometown;
        }

        //@TableId(value = "id", type = IdType.AUTO)
        private Integer id;        //@ApiModelProperty(value = "用户id")
        private String userId;        //@ApiModelProperty(value = "用户昵称")
        private String userName;        //@ApiModelProperty(value = "正式使用的图片，数组格式")
        private String img;        //@ApiModelProperty(value = "1-男 2-女")
        private Integer gender;        //@ApiModelProperty(value = "生日")
        private LocalDate birthday;        //@ApiModelProperty(value = "身高-cm")
        private Integer height;        //@ApiModelProperty(value = "家乡，如：浙江省-杭州市-西湖区")
        private String hometown;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public LocalDate getBirthday() {
            return birthday;
        }

        public void setBirthday(LocalDate birthday) {
            this.birthday = birthday;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public String getHometown() {
            return hometown;
        }

        public void setHometown(String hometown) {
            this.hometown = hometown;
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "id=" + id +
                    ", userId='" + userId + '\'' +
                    ", userName='" + userName + '\'' +
                    ", img='" + img + '\'' +
                    ", gender=" + gender +
                    ", birthday=" + birthday +
                    ", height=" + height +
                    ", hometown='" + hometown + '\'' +
                    '}';
        }
    }

    /**
     * 组装参数，返回
     */
    public static UserInfo buildUserInfo(Map userInfoMap) {
        UserInfo userInfo = new UserInfo();
        Class clazz = userInfo.getClass();
        Field[] names = clazz.getDeclaredFields();
        for (Field field : names) {
            if (userInfoMap.containsKey(field.getName())) {
                field.setAccessible(true);
                try {
                    if (field.getType() == String.class) {
                        field.set(userInfo, userInfoMap.get(field.getName()));
                    }
                    if (field.getType() == Integer.class) {
                        field.set(userInfo, Integer.valueOf((String) userInfoMap.get(field.getName())));
                    }
                    if (field.getType() == LocalDate.class) {
                        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        field.set(userInfo, LocalDate.parse((CharSequence) userInfoMap.get(field.getName()), df));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return userInfo;
    }

    public static void main(String[] args) throws Exception {
        String s="adafafa";
        for (char c : s.toCharArray()) {
            
        }
        Map userInfoMap = new HashMap();
        userInfoMap.put("userName", "梁鹏飞");
        System.out.println(buildUserInfo(userInfoMap));
    }
}