package org.demo.mappers;

import org.demo.po.Paths;

public interface PathsDao {
	public Paths selectPaths(int uid,String path);
	public Paths selectPathsById(int id,String path);
	public Paths insertPaths(Integer uid,String type,String name,String path,String size ,String location);
	public Paths deletePaths(Integer id);
}
