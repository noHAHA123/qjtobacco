//package com.example.sqlwork2.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class FarmerService {
//    @Autowired
//    private UserRepository userRepository;
//
//    // 查询用户列表
//    public List<User> getUsers(String id, String name, String intoCard, String rollCard) {
//        // 实现查询逻辑，根据传入的参数查询用户数据
//        return userRepository.findUsers(id, name, intoCard, rollCard);
//    }
//
//    // 编辑用户
//    public void editUser(Long id, User user) {
//        // 实现编辑逻辑，根据用户ID更新用户数据
//        userRepository.save(user);
//    }
//
//    // 删除用户
//    public void deleteUser(Long id) {
//        // 实现删除逻辑，根据用户ID删除用户数据
//        userRepository.deleteById(id);
//    }
//}
