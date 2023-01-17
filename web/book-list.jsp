<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<title>图书表的增删改查</title>
<style>
a {
	text-decoration: none;
}

.table {
	width: 1000px;
	margin: auto;
}

.batch {
	width: 1000px;
	margin: auto;
	padding-left: 90px;
	padding-top: 10px;
}

.batch>button {
	border: none;
	display: inline-block;
	height: 30px;
	width: 70px;
	font-size: 12px;
	color: white;
	background-color: cornflowerblue;
}

table {
	width: 100%;
}

table>thead>tr {
	display: flex;
	justify-content: space-around;
	border-bottom: 0.5px solid #ccc;
	padding: 10px 0;
}

table>thead>tr>th {
	width: 11.1%;
	text-align: center;
}

table>tbody>tr {
	display: flex;
	justify-content: space-around;
	border-bottom: 0.5px solid #ccc;
	padding: 10px 0;
}

table>tbody>tr>td {
	width: 11.1%;
	text-align: center;
}

.page {
	height: 40px;
	width: 400px;
	margin: 20px auto 0 auto;
	display: flex;
	align-items: center;
	justify-content: center;
}

.page>.pre {
	height: 25px;
	width: 50px;
	font-size: 12px;
	display: flex;
	justify-content: center;
	align-items: center;
	border: 0.5px solid #ccc;
	color: #333;
}

.page>.next {
	height: 25px;
	width: 50px;
	font-size: 12px;
	display: flex;
	justify-content: center;
	align-items: center;
	border: 0.5px solid #ccc;
	color: #333;
}

.page>.mid {
	height: 25px;
	width: 25px;
	font-size: 12px;
	display: flex;
	justify-content: center;
	align-items: center;
	border: 0.1px solid #ccc;
	color: #333;
}

.page>a:hover {
	border: 0.1px solid #333;
	color: darkred;
}
/* 当前页选中样式 */
.curr {
	background-color: cornflowerblue;
}
/* 搜索 */
.search {
	text-align: center;
	margin-bottom: 20px;
}

.search>input {
	width: 600px;
	height: 30px;
	font-size: 16px;
	border-right: none;
	border-top: 1px solid cornflowerblue;
	border-left: 1px solid cornflowerblue;
	border-bottom: 1px solid cornflowerblue;
	outline: none;
}

.search>button {
	border: none;
	height: 34px;
	width: 72px;
	font-size: 14px;
	color: white;
	background-color: cornflowerblue;
}
</style>

</head>
<body>
	<h1 style="text-align: center;">图书管理</h1>
	<!-- 搜索框 -->
	<div class="search">
		<input type="text" name="search" placeholder="请输入搜索条件..." />
		<button style="cursor: pointer;">搜索</button>
	</div>
	<div class="table">
		<table>
			<thead>
				<tr>
					<th><input type="checkbox" id="all" /></th>
					<th>图书编号</th>
					<th>图书名称</th>
					<th>图书作者</th>
					<th>图书价格</th>
					<th>图书分类</th>
					<th>是否上架</th>
					<th>创建时间</th>
					<th>操作</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="tbody">
				
			</tbody>
		</table>
	</div>
	<div class="batch">
		<button type="button" style="cursor: pointer;">批量删除</button>
	</div>
	<!-- 页码 -->
	<div class="page">
		
	</div>

	<a href="BookToAddServlet"
		style="display: block; width: 1000px; height: 40px; border-radius: 3px; color: white; font-size: 14px; line-height: 40px; margin: 10px auto; text-align: center; background-color: cornflowerblue;">添加图书</a>

	<script type="text/javascript" src="js/jquery-3.6.1.min.js"></script>
	<script type="text/javascript">
		var pageNo = 1; //当前页
		var pageSize = 3;//每页显示多少条数
		var pageCount = 0;//总页数
		getPaging(pageNo, pageSize);
		
		var pageNoS = 1;
		var pageSizeS = 2;
		var pageCountS = 0;
		

		/*
			获取分页数据
		 */
		function getPaging(pageNo, pageSize) {

			/*
			通过AJAX获取后端数据
			1. 总页数
			2. 当前页数据（1）
			 */

			$
					.getJSON(
							"BookPageServlet",
							{
								pageNo : pageNo,
								pageSize : pageSize
							},
							function(d) {
								console.log(d);

								//当前页数据为
								var list = d.page;
								pageCount = d.pageCount;

								//声明变量
								var str = "";
								for (var i = 0; i < list.length; i++) {
									str += "<tr>\r\n"
											+ "						<td>\r\n"
											+ "							<input value='"+list[i].bookId+"' class='checkBook' type=\"checkbox\"/>\r\n"
											+ "						</td>\r\n" + "						<td>"
											+ list[i].bookId
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].bookName
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].authorName
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].price
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].category.categoryName
											+ "</td>\r\n"
											+ "						<td>"
											+ (list[i].flag == 0 ? '下架' : '上架')
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].createTime
											+ "</td>\r\n"
											+ "						<td>\r\n"
											+ "							<a onclick=\"if(confirm('确认是否删除?')){return true;}else{return false;}\" href=\"BookDeleteServlet?bookId="
											+ list[i].bookId
											+ "\">删除</a>\r\n"
											+ "						</td>\r\n"
											+ "						<td>\r\n"
											+ "							<a href=\"BookToUpdateServlet?bookId="
											+ list[i].bookId
											+ "\">更新</a>\r\n"
											+ "						</td>\r\n" + "					</tr>";
								}
								// 将拼接好的数据设置到tbody标签中
								$("#tbody").html(str);
								// 首页数据生成后，调用页码生成函数
								joinPage(pageCount, pageNo);
								// 调用样式函数
								showPage(pageNo, "mid")
								// 事件绑定
								bindEvent(pageNo, pageCount);
								// 给复选框绑定事件
								singleBook();
							});
		}
		
		
		/*
			为搜索写的事件绑定
		*/
		function bindEventForSearch(pageNo, pageCount, bookName) {
			
			// 绑定上一页，通过.类名选择器
			$(".pre").click(function() {

				/*
					判断有没有上一页
				 */
				if (pageNoS <= 1) {
					alert("已经是第一页！");
				} else {
					pageNoS -= 1;
					// 重新的获取分页数据
					$
					.getJSON(
							"BookSearchServlet",
							{
								pageNo : pageNoS,
								pageSize : pageSizeS,
								bookName : bookName
							},
							function(d) {
								console.log(d);

								//当前页数据为
								var list = d.page;
								pageCountS = d.pageCount;

								//声明变量
								var str = "";
								for (var i = 0; i < list.length; i++) {
									str += "<tr>\r\n"
											+ "						<td>\r\n"
											+ "							<input value='"+list[i].bookId+"' class='checkBook' type=\"checkbox\"/>\r\n"
											+ "						</td>\r\n" + "						<td>"
											+ list[i].bookId
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].bookName
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].authorName
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].price
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].category.categoryName
											+ "</td>\r\n"
											+ "						<td>"
											+ (list[i].flag == 0 ? '下架' : '上架')
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].createTime
											+ "</td>\r\n"
											+ "						<td>\r\n"
											+ "							<a onclick=\"if(confirm('确认是否删除?')){return true;}else{return false;}\" href=\"BookDeleteServlet?bookId="
											+ list[i].bookId
											+ "\">删除</a>\r\n"
											+ "						</td>\r\n"
											+ "						<td>\r\n"
											+ "							<a href=\"BookToUpdateServlet?bookId="
											+ list[i].bookId
											+ "\">更新</a>\r\n"
											+ "						</td>\r\n" + "					</tr>";
								}
								// 将拼接好的数据设置到tbody标签中
								$("#tbody").html(str);
								// 首页数据生成后，调用页码生成函数
								joinPage(pageCountS, pageNoS);
								// 调用样式函数
								showPage(pageNoS, "mid")
								// 事件绑定
								bindEventForSearch(pageNoS, pageCountS, bookName);
								// 给复选框绑定事件
								singleBook();
							});
				}
			});

			// 绑定中间页
			// 拿到所有的中间页
			var mids = $(".mid");
			for (var i = 0; i < mids.length; i++) {
				// dom节点转jquery对象$()
				$(mids[i]).click(function() {
					// 拿到页码

					pageNoS = parseInt(this.innerHTML); // this.innerHTML拿到的是字符串
					$
					.getJSON(
							"BookSearchServlet",
							{
								pageNo : pageNoS,
								pageSize : pageSizeS,
								bookName : bookName
							},
							function(d) {
								console.log(d);

								//当前页数据为
								var list = d.page;
								pageCountS = d.pageCount;

								//声明变量
								var str = "";
								for (var i = 0; i < list.length; i++) {
									str += "<tr>\r\n"
											+ "						<td>\r\n"
											+ "							<input value='"+list[i].bookId+"' class='checkBook' type=\"checkbox\"/>\r\n"
											+ "						</td>\r\n" + "						<td>"
											+ list[i].bookId
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].bookName
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].authorName
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].price
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].category.categoryName
											+ "</td>\r\n"
											+ "						<td>"
											+ (list[i].flag == 0 ? '下架' : '上架')
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].createTime
											+ "</td>\r\n"
											+ "						<td>\r\n"
											+ "							<a onclick=\"if(confirm('确认是否删除?')){return true;}else{return false;}\" href=\"BookDeleteServlet?bookId="
											+ list[i].bookId
											+ "\">删除</a>\r\n"
											+ "						</td>\r\n"
											+ "						<td>\r\n"
											+ "							<a href=\"BookToUpdateServlet?bookId="
											+ list[i].bookId
											+ "\">更新</a>\r\n"
											+ "						</td>\r\n" + "					</tr>";
								}
								// 将拼接好的数据设置到tbody标签中
								$("#tbody").html(str);
								// 首页数据生成后，调用页码生成函数
								joinPage(pageCountS, pageNoS);
								// 调用样式函数
								showPage(pageNoS, "mid")
								// 事件绑定
								bindEventForSearch(pageNoS, pageCountS, bookName);
								// 给复选框绑定事件
								singleBook();
							});
				});
			}

			// 绑定下一页，通过.类名选择器
			$(".next").click(function() {

				/*
					判断是否有下一页
				 */
				if (pageNoS < pageCountS) {
					pageNoS += 1;
					// 重新的获取分页数据
					$
					.getJSON(
							"BookSearchServlet",
							{
								pageNo : pageNoS,
								pageSize : pageSizeS,
								bookName : bookName
							},
							function(d) {
								console.log(d);

								//当前页数据为
								var list = d.page;
								pageCountS = d.pageCount;

								//声明变量
								var str = "";
								for (var i = 0; i < list.length; i++) {
									str += "<tr>\r\n"
											+ "						<td>\r\n"
											+ "							<input value='"+list[i].bookId+"' class='checkBook' type=\"checkbox\"/>\r\n"
											+ "						</td>\r\n" + "						<td>"
											+ list[i].bookId
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].bookName
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].authorName
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].price
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].category.categoryName
											+ "</td>\r\n"
											+ "						<td>"
											+ (list[i].flag == 0 ? '下架' : '上架')
											+ "</td>\r\n"
											+ "						<td>"
											+ list[i].createTime
											+ "</td>\r\n"
											+ "						<td>\r\n"
											+ "							<a onclick=\"if(confirm('确认是否删除?')){return true;}else{return false;}\" href=\"BookDeleteServlet?bookId="
											+ list[i].bookId
											+ "\">删除</a>\r\n"
											+ "						</td>\r\n"
											+ "						<td>\r\n"
											+ "							<a href=\"BookToUpdateServlet?bookId="
											+ list[i].bookId
											+ "\">更新</a>\r\n"
											+ "						</td>\r\n" + "					</tr>";
								}
								// 将拼接好的数据设置到tbody标签中
								$("#tbody").html(str);
								// 首页数据生成后，调用页码生成函数
								joinPage(pageCountS, pageNoS);
								// 调用样式函数
								showPage(pageNoS, "mid")
								// 事件绑定
								bindEventForSearch(pageNoS, pageCountS, bookName);
								// 给复选框绑定事件
								singleBook();
							});

				} else {
					alert("已经是最后一页！");
				}

			});
		}
		
		
		
		/*
			搜索
		*/
		$(".search>button").click(function(){
			// 初始化
			pageNoS = 1;
			pageSizeS = 2;
			
			// 获取搜索条件
			var s = $(".search>input").val();
			console.log(s);
			if (s != null && s.length > 0) {
				// 说明有值
				$
				.getJSON(
						"BookSearchServlet",
						{
							pageNo : pageNoS,
							pageSize : pageSizeS,
							bookName: s
						},
						function(d) {
							console.log(d);

							//当前页数据为
							var list = d.page;
							pageCountS = d.pageCount;

							//声明变量
							var str = "";
							for (var i = 0; i < list.length; i++) {
								str += "<tr>\r\n"
										+ "						<td>\r\n"
										+ "							<input value='"+list[i].bookId+"' class='checkBook' type=\"checkbox\"/>\r\n"
										+ "						</td>\r\n" + "						<td>"
										+ list[i].bookId
										+ "</td>\r\n"
										+ "						<td>"
										+ list[i].bookName
										+ "</td>\r\n"
										+ "						<td>"
										+ list[i].authorName
										+ "</td>\r\n"
										+ "						<td>"
										+ list[i].price
										+ "</td>\r\n"
										+ "						<td>"
										+ list[i].category.categoryName
										+ "</td>\r\n"
										+ "						<td>"
										+ (list[i].flag == 0 ? '下架' : '上架')
										+ "</td>\r\n"
										+ "						<td>"
										+ list[i].createTime
										+ "</td>\r\n"
										+ "						<td>\r\n"
										+ "							<a onclick=\"if(confirm('确认是否删除?')){return true;}else{return false;}\" href=\"BookDeleteServlet?bookId="
										+ list[i].bookId
										+ "\">删除</a>\r\n"
										+ "						</td>\r\n"
										+ "						<td>\r\n"
										+ "							<a href=\"BookToUpdateServlet?bookId="
										+ list[i].bookId
										+ "\">更新</a>\r\n"
										+ "						</td>\r\n" + "					</tr>";
							}
							// 将拼接好的数据设置到tbody标签中
							$("#tbody").html(str);
							// 首页数据生成后，调用页码生成函数
							joinPage(pageCountS, pageNoS);
							// 调用样式函数
							showPage(pageNoS, "mid")
							// 事件绑定
							bindEventForSearch(pageNoS, pageCountS, s)
							// 给复选框绑定事件
							singleBook();
						});
			} else {
				console.log("值不存在");
			}
		});
		
		
		
		/*
			批量删除,>子类选择器
		*/
		$(".batch>button").click(function(){
			// 获取所有复选框的value值
			// 排除最上面的复选框
			var list = $("input[type='checkBox']:checked").filter(".checkBook");
			// 定义一个空数组
			var arr = [];
			for(var i = 0; i<list.length; i++){
				arr.push(list[i].value);
			}
			
			if(confirm('是否删除')){
				// 通过Ajax调用后台
				// 请求地址，请求参数，回调函数，返回值类型
				$.post("BookBatchDeleteServlet",{'arr[]':arr},function(d){
					console.log(d);
					if(d == 'ok') {
						// 跳转到列表页
						location.href="BookListServlet";
					} else {
						alert("删除失败");
					}
					
				},"text");
			}
			
			
		});
		
		
		
		/*
			绑定全选按钮	id选择器
		*/
		$("#all").click(function(){
			// 先拿到
			var checkBooks = $(".checkBook");
			
			// 循环选中
			for (var i = 0; i<checkBooks.length; i++) {
				checkBooks[i].checked = this.checked;
			}
		}); 
		
		
		/*
			给列表数据多选框绑定事件
		*/
		function singleBook(){
			var checkBooks = $(".checkBook");
			for(var i = 0; i < checkBooks.length; i++) {
				$(checkBooks[i]).click(function(){
					// 先默认第一个框是选中的，然后判断下面的框是否全选
					$("#all")[0].checked=true;
					var checkBooksLater = $(".checkBook");
					for(var j = 0; j < checkBooksLater.length; j++) {
						if(!checkBooksLater[j].checked){
							$("#all")[0].checked=false;
							break;
						}
					}
				});
			}
		}
		
		
		
		/*
			分页事件绑定函数，点哪一页展示哪一页
		 */
		function bindEvent(pageNo, pageCount) {
			// 绑定上一页，通过.类名选择器
			$(".pre").click(function() {

				/*
					判断有没有上一页
				 */
				if (pageNo <= 1) {
					alert("已经是第一页！");
				} else {
					pageNo -= 1;
					// 重新的获取分页数据
					getPaging(pageNo, pageSize);
				}
			});

			// 绑定中间页
			// 拿到所有的中间页
			var mids = $(".mid");
			for (var i = 0; i < mids.length; i++) {
				// dom节点转jquery对象$()
				$(mids[i]).click(function() {
					// 拿到页码

					pageNo = parseInt(this.innerHTML); // this.innerHTML拿到的是字符串
					getPaging(pageNo, pageSize);
				});
			}

			// 绑定下一页，通过.类名选择器
			$(".next").click(function() {

				/*
					判断是否有下一页
				 */
				if (pageNo < pageCount) {
					pageNo += 1;
					// 重新的获取分页数据
					getPaging(pageNo, pageSize);

				} else {
					alert("已经是最后一页！");
				}

			});
		}

		
		/*
			创建设置当前页样式的函数，当前页为选中蓝色效果
		 */
		function showPage(pageNo, className) {
			// 获取中间页的对象集合
			var mids = $("." + className);

			for (var i = 0; i < mids.length; i++) {
				if (mids[i].innerHTML == pageNo) {
					$(mids[i]).addClass("curr");
				}
			}
		}
		

		// 创建动态生成页码的函数
		function joinPage(pageCount, pageNo) {
			// 上一页
			var str = "<a class=\"pre\" href=\"#\">上一页</a>";

			// 中间页
			if (pageCount <= 3) { // 页码是1或者1，2或者1，2，3的情况
				for (var i = 1; i <= pageCount; i++) {
					str += "<a class=\"mid\" href=\"#\">" + i + "</a>";
				}
			} else { // 页码是2，3，4或者3，4，5的情况
				if (pageNo <= 3) {
					for (var i = 1; i <= 3; i++) {
						str += "<a class=\"mid\" href=\"#\">" + i + "</a>";
					}
				} else {
					for (var i = pageNo - 2; i <= pageNo; i++) {
						str += "<a class=\"mid\" href=\"#\">" + i + "</a>";
					}
				}
			}
			// 下一页
			str += "<a class=\"next\" href=\"#\">下一页</a>";

			// 将拼接好的页面设置到指定位置
			$(".page").html(str);
		};
	</script>



</body>
</html>