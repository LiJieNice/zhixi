package com.ruizhiqi.service;

import com.ruizhiqi.dao.UserDAO;
import com.ruizhiqi.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Override
    public List<User> showAll() {

        return userDAO.showAll();

    }
}
