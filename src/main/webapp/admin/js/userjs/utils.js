/**
 * 检测字符串在去掉首尾空格后是否为空
 * @param {String} texts 要检测的字符串
 * @returns {Boolean} 如果字符串为空，返回true，否则false
 */
function isEmpty(texts) {
    var result = false;
    
    if (!texts || !texts.trim()) {
        result = true;
    }
    
    return result;
}



/**
 * 根据请求返回的结果，检测会话是否已经失效
 * @param {String} info 请求返回的结果
 * @returns {Boolean} 如果会话已经失效，则返回true，否则false
 */
function isSessionTimeout(info) {
    var timeoutPattern = "form-signin";
    var isTimeout = false;
    var s = new String(info);
    
    if (s.indexOf(timeoutPattern) > 0)
        isTimeout = true;
    
    return isTimeout;
}

/**
 * 跳转到登录界面
 * 
 */
function relogin() {
    alert("您的会话已失效，请重新登录，谢谢！");
    window.location.href = "../admin/login.html";
}

/**
 * 网络资源访问错误处理方法
 * @param {String} error 返回的错误文本信息
 * @param {type} status 返回的HTTP状态码
 */
function errorHandler(error, status) {
    if (404 === status)
        alert("(；′⌒`)我暂时和数据中心失去了联系，您待会再试试看……");
    else if (isSessionTimeout(error)) {
        relogin();
    }    
}

/**
 * 回退到历史记录里的上一层
 */
function goBack() {
    history.back();
}

function refresh() {
    window.location.reload();
}