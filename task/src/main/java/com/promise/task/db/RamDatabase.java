package com.promise.task.db;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.promise.task.Task;

@Component
@Scope(value="singleton")
public class RamDatabase implements DatabaseInterface {
	
	private static Map<String, Task> db;
	
	public RamDatabase() {
		db = new HashMap<String, Task>();
		System.out.println("Ram Database created.");
	}

	@Override
	public Task getTask(String id) {
		return db.get(id);
	}

	@Override
	public void setTask(Task task) {
		db.put(task.getId(), task);
		System.out.println("Task count = " + db.size());
	}

}
