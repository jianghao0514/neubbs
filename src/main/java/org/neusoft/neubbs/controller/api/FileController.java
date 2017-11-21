package org.neusoft.neubbs.controller.api;

import org.neusoft.neubbs.constant.ajax.AjaxRequestStatus;
import org.neusoft.neubbs.constant.api.ApiMessage;
import org.neusoft.neubbs.constant.api.ParamConst;
import org.neusoft.neubbs.constant.log.LogWarn;
import org.neusoft.neubbs.constant.secret.SecretInfo;
import org.neusoft.neubbs.controller.annotation.AccountActivation;
import org.neusoft.neubbs.controller.annotation.LoginAuthorization;
import org.neusoft.neubbs.controller.exception.AccountErrorException;
import org.neusoft.neubbs.controller.exception.DatabaseOperationFailException;
import org.neusoft.neubbs.controller.exception.FileUploadErrorException;
import org.neusoft.neubbs.dto.ResponseJsonDTO;
import org.neusoft.neubbs.entity.UserDO;
import org.neusoft.neubbs.entity.properties.NeubbsConfigDO;
import org.neusoft.neubbs.service.IFileService;
import org.neusoft.neubbs.service.IUserService;
import org.neusoft.neubbs.utils.CookieUtil;
import org.neusoft.neubbs.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * 文件 api
 *      + 上传用户头像
 *
 * @author Suvan
 */
@Controller
@RequestMapping("/api/file")
public class FileController {

    private final IUserService userService;
    private final IFileService fileService;
    private final NeubbsConfigDO neubbsConfig;

    /**
     * Constructor
     */
    @Autowired
    public FileController(IUserService userService, IFileService fileService,
                          NeubbsConfigDO neubbsConfig) {
        this.userService = userService;
        this.fileService = fileService;
        this.neubbsConfig = neubbsConfig;
    }

    /**
     * 1.上传用户头像（新上传的会覆盖旧的）
     *
     * 业务流程
     *      - 检查用户图片规范（不为空，后缀格式，大小不超过 5MB）
     *      - 压缩文件
     *      - 构建服务器端储存文件名
     *      - 传输文件
     *      - 更改数据库（forum_user 的 image）
     *      - 返回成功状态
     *
     * @param multipartFile 用户上传的文件对象
     * @param request http请求
     * @return ResponseJsonDTO 响应JSON传输对象
     * @throws FileUploadErrorException 文件上传错误异常
     * @throws AccountErrorException 账户错误异常
     * @throws DatabaseOperationFailException 数据库操作异常
     */
    @LoginAuthorization @AccountActivation
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJsonDTO uploadUserImage(@RequestParam("image")MultipartFile multipartFile,
                                           HttpServletRequest request)
            throws FileUploadErrorException, AccountErrorException, DatabaseOperationFailException {

        fileService.checkUserImageNorm(multipartFile);

//        MultipartFile compressedFile = fileService.compressFile(multipartFile)

        String authentication = CookieUtil.getCookieValue(request, ParamConst.AUTHENTICATION);
        UserDO cookieUser = JwtTokenUtil.verifyToken(authentication, SecretInfo.JWT_TOKEN_LOGIN_SECRET_KEY);
        if (cookieUser == null) {
            throw new AccountErrorException(ApiMessage.TOKEN_EXPIRED).log(LogWarn.ACCOUNT_05);
        }
        String fileName
                = fileService.buildServerKeppUserImageFileName(cookieUser.getId(), multipartFile.getOriginalFilename());
        String serverSaveUserImageFilePath
                = request.getServletContext().getRealPath(neubbsConfig.getUserImageUploadPath());
        fileService.transferToServer(multipartFile, serverSaveUserImageFilePath, fileName);

        userService.uploadUserImage(cookieUser.getName(), fileName);

        return new ResponseJsonDTO(AjaxRequestStatus.SUCCESS, ApiMessage.UPLOAD_SUCCESS);
    }
}
