<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>

<HTML>
<HEAD>
    <title>角色权限管理</title>
    <LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
    <script language="javascript"
            src="${pageContext.request.contextPath }/script/function.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/script/pub.js"></script>
    <script language="javascript"
            src="${pageContext.request.contextPath }/script/jquery-1.4.2.js"></script>
    <script language="javascript">

        function saveRole(){
            document.Form2.roleID.value=document.Form1.roleID.value;
            document.Form2.action="${pageContext.request.contextPath }/system/elecRoleAction_save.do";
            document.Form2.submit();
        }

        function selectRole() {
            /**
             * 没有选择角色类型
             */
            if (document.Form1.roleID.value == "0") {
                document.Form1.action = "${pageContext.request.contextPath }/system/elecRoleAction_home.do";
                document.Form1.submit();
            }
            /**
             * 选择角色类型
             * 说明Pub.submitActionWithForm('Form2','elecRoleAction_edit.do','Form1');
             * 使用ajax封装的js代码，pub.js，原理是
             * 在roleIndex.jsp中存在2个表单，1个是Form1,1个是From2
             * 使用ajax向服务器发送请求，传递表单Form1的参数，在服务器端进行处理，将处理后的结果返回到roleEdit.jsp中，
             * 最后使用innerHTML，将roleEdit.jsp的整个页面替换到roleIndex.jsp的Form2中
             */
            else {
                Pub.submitActionWithForm('Form2', 'elecRoleAction_edit.do', 'Form1');
            }
        }

        function displayuser() {
            if ($("#dataUser").css("display") == "none") {
                $("#userflag").text("用户分配 关闭");
                $("#dataUser").css("display", "");
            }
            else {
                $("#userflag").text("用户分配 打开");
                $("#dataUser").css("display", "none");
            }
        }
        function displaypermission() {
            if ($("#dataPopedom").css("display") == "none") {
                $("#permissionflag").text("权限分配 关闭");
                $("#dataPopedom").css("display", "");
            }
            else {
                $("#permissionflag").text("权限分配 打开");
                $("#dataPopedom").css("display", "none");
            }
        }

        /**
         * 权限：全部选中/不选中
         */
        function checkAllOper(oper) {
            $("input[type='checkbox'][name='selectoper']").attr("checked", oper.checked);
        }

        /**
         * 用户：全部选中/不选中
         **/
        function checkAllUser(user) {
            $("input[type='checkbox'][name='selectuser']").attr("checked", user.checked);
        }

        /**
         * 选中复选框，触发事件
         **/
        function goSelect(id) {
            //按照_符号分隔
            var array = id.split("_");
            if (array[0] == array[1]) {//此时说明操作的（父）节点
                //选中父
                if ($("#" + id)[0].checked) {
                    //子都选中
                    $("input[type='checkbox'][name='selectoper'][id^='" + array[0] + "']").attr("checked", true);
                }
                //取消父
                else {
                    //子都取消
                    $("input[type='checkbox'][name='selectoper'][id^='" + array[0] + "']").attr("checked", false);
                }
            }
            else {//说明此时操作的子设置中的一个(子)
                //当选中子设置中的一个，则父一定被选中
                if ($("#" + id)[0].checked) {
                    $("input[type='checkbox'][name='selectoper'][id^='" + array[0] + "'][id$='" + array[0] + "']").attr("checked", true);
                }
                //当取消子设置中的一个
                else {
                    //先查找子设置的对象
                    var $check = $("input[type='checkbox'][name='selectoper'][id^='" + array[0] + "']:not([id$='" + array[0] + "'])");
                    //遍历子设置的对象
                    /**
                     * flag:用于判断当前子设置的对象是否有被选中
                     *   * flag=false，子对象都没有被选中，此时父要被取消
                     *   * flag=true，子对象中至少有一个被选中，此时不做任何操作
                     */
                    var flag = false;
                    $check.each(function (index, domEle) {
                        if (domEle.checked) {
                            flag = true;
                            return false;
                        }
                    });
                    if (!flag) {
                        $("input[type='checkbox'][name='selectoper'][id^='" + array[0] + "'][id$='" + array[0] + "']").attr("checked", false);
                    }
                }
            }
        }
    </script>
</HEAD>

<body>
<Form name="Form1" id="Form1" method="post" style="margin:0px;">
    <table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
        <TBODY>
        <tr>
            <td class="ta_01" colspan=2 align="center"
                background="${pageContext.request.contextPath }/images/b-info.gif">
                <font face="宋体" size="2"><strong>角色管理</strong></font>
            </td>
        </tr>
        <tr>
            <td class="ta_01" colspan=2 align="right" width="100%" height=10>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="right" width="35%">角色类型&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td class="ta_01" align="left" width="65%">
                <s:select list="#request.roleList" name="roleID" id="roleID"
                          listKey="roleID" listValue="roleName"
                          headerKey="0" headerValue="请选择"
                          cssClass="bg" cssStyle="width:180px" onchange="selectRole()">
                </s:select>
            </td>
        </tr>
        <tr>
            <td class="ta_01" align="right" colspan=2 align="right" width="100%" height=10></td>
        </tr>
        </TBODY>
    </table>
</form>

<form id="Form2" name="Form2"
      action="${pageContext.request.contextPath }/system/elecRoleAction_home.do" method="post"
      style="margin:0px;">

    <table cellSpacing="1" cellPadding="0" width="90%" align="center" bgColor="#f5fafe" border="0">
        <tr>
            <td>
                <fieldset
                        style="width:100%; border : 1px solid #73C8F9;text-align:left;COLOR:#023726;FONT-SIZE: 12px;">
                    <legend align="left">权限分配</legend>

                    <table cellSpacing="0" cellPadding="0" width="90%" align="center"
                           bgColor="#f5fafe" border="0">
                        <tr>
                            <td class="ta_01" colspan=2 align="left" width="100%">

                                <table cellspacing="0" align="center" width="100%" cellpadding="1"
                                       rules="all" bordercolor="gray" border="1"
                                       style="BORDER-RIGHT:gray 1px solid; BORDER-TOP:gray 1px solid; BORDER-LEFT:gray 1px solid; WORD-BREAK:break-all; BORDER-BOTTOM:gray 1px solid; BORDER-COLLAPSE:collapse; BACKGROUND-COLOR:#f5fafe; WORD-WRAP:break-word">
                                    <s:if test="#request.popedomList!=null && #request.popedomList.size()>0">
                                    <s:set value="%{'1'}" var="flag" scope="request"></s:set>
                                    <s:iterator value="#request.popedomList" var="popedom">
                                    <!-- 父节点 -->
                                    <s:if test="#popedom.isParent">
                                    <s:if test="#request.flag=='2'">
                                        </td>
                                        </tr>
                                        <s:set value="%{'1'}" var="flag" scope="request"></s:set>
                                    </s:if>
                                    <tr onmouseover="this.style.backgroundColor = 'white'"
                                        onmouseout="this.style.backgroundColor = '#F5FAFE';">
                                        <td class="ta_01" align="left" width="18%" height="22"
                                            background="../images/tablehead.jpg">
                                            <input type="checkbox" name="selectoper"
                                                   id="<s:property value="#popedom.mid"/>_<s:property value="#popedom.mid"/>"
                                                   value="" onClick='goSelect(this.id)'>
                                                <s:property value="#popedom.name"/>：
                                            </s:if>
                                            <!-- 子节点 -->
                                            <s:else>
                                            <s:if test="#request.flag=='1'">
                                        <td class="ta_01" align="left" width="82%" height="22">
                                                <s:set value="%{'2'}" var="flag"
                                                       scope="request"></s:set>
                                            </s:if>
                                            <div>
                                                <input type="checkbox" name="selectoper"
                                                       id="<s:property value="#popedom.pid"/>_<s:property value="#popedom.mid"/>"
                                                       value="" onClick='goSelect(this.id)'>
                                                <s:property value="#popedom.name"/>
                                            </div>
                                            </s:else>
                                            </s:iterator>
                                            </s:if>
                                </table>

                            </td>
                        </tr>
                        <input type="hidden" name="roleID"/>
                    </table>
                </fieldset>
            </td>
        </tr>
    </table>
</Form>
</body>
</HTML>
