package com.jxy.springMvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;

@Controller
@RequestMapping("file")
public class FileController {
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "file/fileUpload";
    }

    @ResponseBody
    @RequestMapping("/upload")
    public void uploadFile(MultipartFile file, HttpServletRequest request) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                String filePath = file.getOriginalFilename();// 转存文件
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @ResponseBody
    @RequestMapping("/uploadPa")
    public void uploadByPart(@RequestPart("file") Part part, HttpServletRequest request){
        // 判断文件是否为空
        try {
            // 文件保存路径

            String filePath = part.getSubmittedFileName();// 转存文件
            part.write(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
