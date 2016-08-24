package com.linhao007.www.BuildersPattern;

/**
 * Created by www.linhao007.com on 2016-8-24.
 */
public class Persion {
    private String name;
    private String sex;
    private int age;
    private String education;

    /**
     * 1、将构造函数私有化   禁止外界直接创建对象
     * 2、私有构造函数指定入参为建造者  只有建造者才能赋值  创建对象
     */

    private Persion(Builder builder) {
        this.name = builder.name;
        this.sex = builder.sex;
        this.education = builder.education;
        this.age = builder.age;
    }

    /**
     * 建立内部类  用于代理管理复杂对象的属性
     */
    public static class Builder {
        private String name;
        private String sex;
        private int age;
        private String education;

        /**
         * 提供一个公有的构造函数用于外界访问
         */
        public Builder() {
        }

        /**
         * 开始管理复杂对象的属性
         * 每一次的属性发改变都返回build引用  相当于收集参数
         * 其中add开头方法表示添加的组件
         */
        public Builder addName(String name) {
            this.name = name;
            return this;
        }

        public Builder addSex(String sex) {
            this.sex = sex;
            return this;
        }

        public Builder addEducation(String education) {
            this.education = education;
            return this;
        }

        public Builder addAge(int age) {
            this.age = age;
            return this;
        }

        /**
         * 最后将建筑者的组件添加到复杂对象中
         * 这个过程就是建造
         */
        public Persion build() {
            return new Persion(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }


}
