package com.lanou.action;

import com.lanou.domain.Department;
import com.lanou.domain.Post;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蓝鸥科技有限公司  www.lanou3g.com.
 */
public class StaffAction extends ActionSupport {
    private List<Department> departList;//存储所有部门的集合

    {
        /*初始化部门集合数据*/
        departList = new ArrayList<>();

        /*部门添加*/
        for (int i = 0; i < 3; i++) {
            Department department = new Department();
            department.setId(i);//部门id
            department.setPname("部门" + i);//部门名字

            List<Post> postList = new ArrayList<>();//存储当前部门下的所有职务集合
            /*部门中的职务集合添加*/
            for (int j = 0; j < 5; j++) {
                Post post = new Post();
                post.setId(j);//职务id
                post.setPname(i + "职务" + j);//职务名称

                postList.add(post);//将职务加到职务集合中
            }

            department.setPostList(postList);//将职务集合设置到部门对象中

            departList.add(department);//将部门对象添加到部门集合中
        }
    }

    private String departId;//发起部门id查询时传递过来的部门id


    /**
     * 查找所有部门
     **/
    public String findDepartment() {


        return SUCCESS;
    }

    /**
     * 根据部门id查找职务集合
     **/
    public String findPostsByPid() throws IOException {
        System.out.println("要查询的部门id--->>" + departId);

        int dId = Integer.parseInt(departId);

        //根据部门id获取该部门下的所有职务集合
        List<Post> postList = departList.get(dId).getPostList();

        //封装json数据
        JsonConfig jsonConfig = new JsonConfig();

        String jsonArray = JSONArray.fromObject(postList, jsonConfig).toString();

        //将json数据作为响应内容进行返回
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(jsonArray);

        return null;//什么都不做
    }

     private  List<Post> postList;

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public String findPostListByPid2(){
        int did = Integer.parseInt(departId);
       postList = departList.get(did).getPostList();
        return SUCCESS;
    }

    public List<Department> getDepartList() {
        return departList;
    }

    public void setDepartList(List<Department> departList) {
        this.departList = departList;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }


}
