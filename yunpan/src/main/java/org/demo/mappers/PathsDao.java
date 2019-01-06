package org.demo.mappers;

import java.util.HashMap;
import java.util.List;

import org.demo.po.Paths;

public interface PathsDao {
	public Paths selectPaths(int uid,String path);
	public Paths selectDirPaths(int uid,String path);
	public Paths selectOthersPaths(int uid,String path);
	public Paths selectPathsById(int id);
	public Paths selectPathsByName(int uid,String name,String path);
	public Paths selectPathsByNameAndType(int uid,String name,String path,String type);
	public Paths selectDirPathsByName(int uid,String name,String path);
	public Paths selectDirPathsLikeName(int uid,String path);
	public Paths selectOthersPathsLikeName(int uid,String path);
	public Paths insertPaths(Integer uid,String type,String name,String path,String size ,String location);
	public Paths deletePaths(Integer id);
	public Paths deletePathsList(List list);
	public Paths updatePathName(HashMap map);
	public Paths updatePathLocation(HashMap map);
}
