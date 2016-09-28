<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="../static/bootstrap3/css/bootstrap.min.css">
<script type="text/javascript"
	src="../static/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript"
	src="../static/bootstrap3/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="../static/css/yisou.css">
<title>易搜</title>
<script type="text/javascript">
	
		
	
		function submitSearch(page){
			document.getElementById("page").value=page;
			document.getElementById("searchForm").submit();
		}
		function inputChange(){
			document.getElementById("page").value=1;
		}
	</script>
<script type="text/javascript">
	$(function(){
		$(function(){
			//初始化谁被中
			var searchType = $("#searchType").val();
			$(".search-lable li").attr("class","");
			$("#"+searchType+"").attr("class","active");
			
			$("#searchBtn").click(function(){
				$("#page").val("1");
				$("#searchForm").submit();
			})
			$("li:not(.active)").click(function(){
				$("#page").val("1");
				var searchType=$(this).children("span").html();
				$("#searchType").val(searchType);
				$(this).siblings("li").attr("class","");
				$(this).attr("class","active");
				$("#searchForm").submit();
			})
			
			
		})
	})
	</script>
</head>
<body>
	<div class="container-fluid">
		<form method="get" action="search.do" id="searchForm">
			<div class="row search-top">
				<div class="col-md-1" style="padding: 0px;">
					<img alt="" src="../static/img/logo.png"
						style="height: 32px; margin-top: 11px; padding-left: 7px;">
				</div>
				<div class="col-md-6"
					style="margin-top: 11px; padding: 0px; padding-left: 2px;">
					<div class="search-content">
						<input type="text" name="searchType" class="hide" id="searchType"
							value="${searchType}"></input> <input type="text" name="page"
							value='${page}' id="page" class="hide"></input>

						<div class="input-group">
							<input type="text" onclick="inputChange();" class="form-control"
								id="q" value="${search }" autocomplete="off" placeholder="搜索网盘"
								name="searchContent"> <span class="input-group-btn">
								<button class="btn btn-default" type="button" id="searchBtn">易搜一下</button>
							</span>
						</div>

					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 col-md-offset-1">
					<div class="search-tip-top">
						<div id="sug"></div>
					</div>
				</div>
			</div>
			<div style="margin-top: 56px;">
				<div class="row search-lable">
					<div class="col-md-6 col-md-offset-1" style="margin-top: 7px;">
						<ul class="nav nav-pills">
							<li role="presentation" id="pan" class=""><span class="hide">pan</span>
								<a>网盘 </a></li>
							<li role="presentation" id="file" class="active"><span
								class="hide">file</span> <a>文档 </a></li>
							<li role="presentation" id="jar" class=""><span class="hide">jar</span>
								<a>Jar</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="row search-main">
				<div class="col-md-7 col-md-offset-1">
					<div class="searchTipContent">搜索内容如下:</div>
					<c:forEach items="${searchContent.searchInfos}" var="item">
						<div class="panel panel-default">
							<div class="panel-heading">
								<a href="${item.unescapedUrl}" target="_Blank"
									title="${item.unescapedUrl }" class="search-item-title ">${item.title}</a>
							</div>
							<div class="panel-body">
								<div class="row">
									<c:if test="${item.imgUrl!=null}">
										<div class="col-md-2">
											<a href="${item.imgUrl }" class="img-rounded searchImg">
												<img src="${item.imgUrl }" alt="...">
											</a>
										</div>
										<div class="col-md-10 search-item-title">
											<div>${item.content }</div>
											<div class="item-url">页面链接:${item.unescapedUrl }</div>
										</div>
									</c:if>
									<c:if test="${item.imgUrl==null}">
										<div class="item-content search-item-content">${item.content }</div>
										<div class="item-content search-item-content item-url">页面链接:${item.url }</div>
									</c:if>
								</div>
							</div>
						</div>
					</c:forEach>


					<div class="footer-nav-wrapper">
						<nav>
							<ul class="pagination" id="pageNav">
								<!-- 判断页数 -->
								<!-- 判断开始 -->
								<c:if test="${searchContent.pageSum<=10}">
									<c:set var="begin" value="1"></c:set>
									<c:set var="end" value="${searchContent.pageSum}"></c:set>
								</c:if>
								<c:if test="${searchContent.pageSum>10}">
									<c:if test="${page>5}">
										<c:if test="${searchContent.pageSum-page>5}">
											<c:set var="begin" value="${page-4 }"></c:set>
											<c:set var="end" value="${page+5}"></c:set>
										</c:if>
									</c:if>
									<c:if test="${page<=5}">
										<c:set var="begin" value="1"></c:set>
										<c:set var="end" value="10"></c:set>
									</c:if>
								</c:if>
								<c:forEach var="i" begin="${begin }" end="${end }">
									<c:if test="${page==i}">
										<li class="active"><a>${i}</a></li>
									</c:if>
									<c:if test="${page!=i}">
										<li><a onclick="submitSearch(${i})">${i}</a></li>
									</c:if>
								</c:forEach>
							</ul>
						</nav>

						<div class="" style="float: right;">
							<p style="text-indent: 2em; font-size:12px;">
								本站源码：大神请忽视-----<a href="https://github.com/smallleaf/yisou">召唤传送门</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</form>

	</div>
</body>
</html>
<script type="text/javascript" src="../static/js/base.js"></script>