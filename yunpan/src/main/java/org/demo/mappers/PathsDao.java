package org.demo.mappers;

import java.util.HashMap;
import java.util.List;

import org.demo.po.Paths;

public interface PathsDao {
	public Paths selectPaths(int uid,String path);
	public Paths selectPathsById(int id);
	public Paths selectPathsByName(String name,String path);
	public Paths selectPathsByPath(String path);
	public Paths insertPaths(Integer uid,String type,String name,String path,String size ,String location);
	public Paths deletePaths(Integer id);
	public Paths deletePathsList(List list);
	public Paths updatePathName(HashMap map);
	public Paths updatePathLocation(HashMap map);
}
