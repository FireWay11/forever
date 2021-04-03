package com.lovezc.forever.controller;

import com.luhuiguo.fastdfs.domain.StorePath;
import com.luhuiguo.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
public class UploadController {

	@Autowired
	 private FastFileStorageClient storageClient;

	@Autowired
	JdbcTemplate jdbcTemplate;

	// MultipartFile是用来接收上传的文件
	// myFile的名字必须和上传的表单的名字一样
    @ResponseBody
	@PostMapping("myUpload")
	public String upload(MultipartFile myFile) throws IOException {
		// myFile.getOriginalFilename():取到文件的名字
		// FilenameUtils.getExtension(""):取到一个文件的后缀名
		String extension = FilenameUtils.getExtension(myFile.getOriginalFilename());

		// group1:指storage服务器的组名
		// myFile.getInputStream():指这个文件中的输入流
		// myFile.getSize():文件的大小
		// 这一行是通过storageClient将文件传到storage容器
		StorePath uploadFile = storageClient.uploadFile("group1", myFile.getInputStream(), myFile.getSize(), extension);

		// 上传数据库
		String sql = "insert into file(filename,groupname,filepath) values(?,?,?)";
		jdbcTemplate.update(sql, myFile.getOriginalFilename(), uploadFile.getGroup(), uploadFile.getPath());

		// 返回它在storage容器的的路径
		return uploadFile.getFullPath();
	}

	@GetMapping("/toUpload")
    public String toUpload(){
        return "upload";
    }
	@GetMapping("/toDownload")
    public String toDownload(){
        return "download";
    }
    @GetMapping("/fdownload/{id}")
    public void download(@PathVariable String id, HttpServletResponse response) throws IOException {

        List query = jdbcTemplate.query("select * from file where fileid=" + id, new ColumnMapRowMapper());
        Map map = (Map) query.get(0);
        // 解决中文文件名下载后乱码的问题
        String filename = URLEncoder.encode(map.get("filename").toString(), "utf-8");
        // 告诉浏览器 下载的文件名
        response.setHeader("Content-Disposition", "attachment; filename=" + filename + "");
        String groupName = map.get("groupName").toString();
        String filepath = map.get("filepath").toString();
        // 将文件的内容输出到浏览器 fastdfs
        byte[] downloadFile = storageClient.downloadFile(groupName, filepath);
        response.getOutputStream().write(downloadFile);
    }

}
