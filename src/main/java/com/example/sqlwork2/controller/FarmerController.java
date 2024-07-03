//package com.example.sqlwork2.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/users")
//public class FarmerController {
//
//    @Autowired
//    private UserService userService;
//
//    // 查询用户列表
//    @GetMapping
//    public List<User> getUsers(@RequestParam(required = false) String id,
//                               @RequestParam(required = false) String name,
//                               @RequestParam(required = false) String intoCard,
//                               @RequestParam(required = false) String rollCard) {
//        return userService.getUsers(id, name, intoCard, rollCard);
//    }
//
//    // 编辑用户
//    @PutMapping("/{id}")
//    public void editUser(@PathVariable Long id, @RequestBody User user) {
//        userService.editUser(id, user);
//    }
//
//    // 删除用户
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//    }
//}
