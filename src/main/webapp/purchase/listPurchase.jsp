<%-- 구매목록 화면 --%>
<%-- listPurchase.do --%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>

<html>

	<head>
	
		<title>구매목록 조회</title>
	
		<link rel="stylesheet" href="/css/admin.css" type="text/css">
	
		<script type="text/javascript">
			function fncGetUserList() {
				document.detailForm.submit();
			}
			
		</script>
		
		<style>
	        a.disabled {
	            pointer-events: none; /* 링크 클릭 비활성화 */
	            color: #FFFFFF; /* 비활성화 된 링크의 색상 변경 */
	            text-decoration: none; /* 링크 밑줄 제거 */
	            cursor: default; /* 기본 커서로 변경 */
	        }
	        
    	</style>
		
	</head>
	
	<body bgcolor="#ffffff" text="#000000">
	
		<div style="width: 98%; margin-left: 10px;">
		
			<form name="detailForm" action="/listPurchase.do" method="post">
			
				<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
					<tr>
						<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
						<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="93%" class="ct_ttl01">
										${user.userName } 님의 구매목록 조회
									</td>
								</tr>
							</table>
						</td>
						<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
					</tr>
				</table>
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
				
					<tr>
						<td colspan="11">전체 ${paging.total } 건수, 현재 ${paging.currentPage } 페이지</td>
					</tr>
					<tr>
						<td class="ct_list_b" width="50">No</td>
						<td class="ct_line02"></td>
						<td class="ct_list_b" width="100">상품명</td>
						<td class="ct_line02"></td>
						<td class="ct_list_b" width="100">받는사람</td>
						<td class="ct_line02"></td>
						<td class="ct_list_b">전화번호</td>
						<td class="ct_line02"></td>
						<td class="ct_list_b">구매일자</td>
						<td class="ct_line02"></td>
						<td class="ct_list_b">배송현황</td>
						<td class="ct_line02"></td>
						<td class="ct_list_b">정보수정</td>
					</tr>
					<tr>
						<td colspan="11" bgcolor="808285" height="1"></td>
					</tr>
					
					<c:forEach var="purchase" items="${list }" varStatus="status">
					
						<tr class="ct_list_pop">
							<td align="center">
								${status.count }
							</td>
							
							<td></td>
							<%-- 상품명 --%>
							<td align="left">
								<a href="/getProduct.do?prodNo=${purchase.purchaseProd.prodNo }">
									${purchase.purchaseProd.prodName }
								</a>
							</td>
							
							<td></td>
							
							<%-- 받는사람 --%>
							<td align="left">
								${purchase.receiverName }
							</td>
							
							<td></td>
							
							<%-- 전화번호 --%>
							<td align="left">
								${purchase.receiverPhone }
							</td>
							
							<td></td>
							
							<%-- 구매일자 --%>
							<td align="left">
								${purchase.orderDate }
							</td>
							
							<td></td>
							
							<%-- 배송현황 --%>
							<td align="left">
								${tranCodeMap[purchase.tranCode] }
							</td>
							
							<td></td>
							
							<%-- 정보수정(배송확인버튼) --%>
							<td align="left">
							
								<c:choose>
								
									<c:when test="${purchase.tranCode == 3 }">
									
										<a href="/updateTranCode.do?tranNo=${purchase.tranNo }
																	&tranCode=${purchase.tranCode }
																	&page=${paging.currentPage }">
											물건도착
										</a>
									
									</c:when>
									
									<%-- 정보수정 : 배송완료전이면 배송지 수정하게끔 **4=배송완료 --%>
									<c:when test="${purchase.tranCode > 1 && purchase.tranCode < 4 }">
									
										<a href="/getPurchase.do?tranNo=${purchase.tranNo }">
											배송정보 확인
										</a>
										
									</c:when>

								</c:choose>
								
							</td>
						</tr>
						
					</c:forEach>
					
					<c:if test="${paging.total == 0 }">
						<tr class="ct_list_pop">
							<table>
								<tr>
									<td></td>
									<td align="center">
										<h3>구매이력이 없습니다.</h3>
									</td>
								</tr>
							</table>
						</tr>
					</c:if>
					
				</table>
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
					
					<tr>
						<td align="center">
					
							<a href="/listPurchase.do?page=1"
							${(paging.left)? "":"class='disabled'" }>
								<span>◀</span>
							</a>
							
							&nbsp;
							
							<a href="/listPurchase.do?page=${paging.start - 1 }"
							${(paging.left)? "":"class='disabled'" }>
								<span>이전</span>
							</a>
					
							&nbsp;&nbsp;
						
							<c:forEach begin="${paging.start }" end="${paging.end }" varStatus="status">
								<a href="/listPurchase.do?page=${status.count }" 
								${(paging.currentPage==status.count)? "style='font-weight: bold; font-size: 15px'" : "" }>
									${status.count }
								</a> 
							</c:forEach>

							&nbsp;&nbsp;
							
							<a href="/listPurchase.do?page=${paging.end + 1 }"
							${(paging.right)? "":"class='disabled'" }>
								<span>다음</span>
							</a>
							
							&nbsp;
							
							<a href="/listPurchase.do?page=${paging.totalPage }"
							${(paging.right)? "":"class='disabled'" }>
								<span>▶</span>
							</a>
					
						</td>
					</tr>
					
				</table>
			
			<!--  페이지 Navigator 끝 -->
			</form>
		
		</div>
		
		<br><br>
		
		<%	System.out.println("\tinclude 발생: listPurchaseHistory.jsp"); %>
		<%--<jsp:include page="listPurchaseHistory.jsp"></jsp:include>--%>
		<%@ include file="listPurchaseHistory.jsp" %>
		
	</body>
</html>
