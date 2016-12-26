package com.promise.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.promise.task.dao.TaskDaoInterface;

@Service("testService")
@Transactional
public class TestServiceImpl implements TestServiceInterface
{
    @Autowired
    private TaskDaoInterface taskDao;

    public TestServiceImpl()
    {
        if (taskDao == null)
        {

        }
    }
}
