<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<ArrayList<String>> list = (ArrayList<ArrayList<String>>) request.getAttribute("s3_list");

	ArrayList<String> list_test = new ArrayList<String>();
	list_test.add("hi");
	list_test.add("hello");
	list_test.add("what");

	ArrayList<String> list_test2 = new ArrayList<String>();
	list_test2.add("1");
	list_test2.add("2");
	list_test2.add("3");

	ArrayList<ArrayList<String>> list_array = new ArrayList<ArrayList<String>>();
	list_array.add(list_test);
	list_array.add(list_test2);
%>
<!DOCTYPE html>
<html>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Oswald">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Open Sans">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
h1, h2, h3, h4, h5, h6 {
	font-family: "Oswald"
}

body {
	font-family: "Open Sans"
}
</style>
<body class="w3-light-grey">


	<div class="w3-content" style="max-width: 1600px">
		<!-- Header -->
		<header class="w3-container w3-center w3-padding-48 w3-white">
			<h1 class="w3-xxxlarge">
				<b>S3 upload & veiw list</b>
			</h1>
		</header>




		<!-- Grid -->
		<div class="w3-row w3-padding w3-border">

			<!-- Blog entries -->
			<div class="w3-col">

				<!-- Blog entry -->
				<div class="w3-container w3-white w3-margin w3-padding-large">
					<div class="w3-center">
						<h3>파일업로드</h3>
					</div>

					<div class="w3-justify">
						<form action="s3upload" method="post"
							enctype="multipart/form-data">
							<label>파일</label> <input type="file" name="uploadfile"
								required="required"> <input type="submit" value="제출">
						</form>
					</div>

				</div>

				<div class="w3-container w3-white w3-margin w3-padding-large">
					<div class="w3-center">
						<h3>파일List</h3>
					</div>

					<div class="w3-justify">
						<form action="s3list" method="post">
							<input type="submit" value="새로고침">
						</form>
					</div>
					<div>
						<table border="1" width="1000" class="w3-table-all w3-hoverable">
							<tr>
								<td width="100">이름</td>
								<td width="150">url</td>
							</tr>
							<%
								for (int i = 0; i < list.size(); i++) {
							%>
							<tr>
								<%
									for (int j = 0; j < list.get(i).size(); j++) {
											String str = list.get(i).get(j);
								%>
								<td><%=str%></td>
								<%
									}
								%>
							</tr>
							<%
								}
							%>

						</table>
					</div>

				</div>
				<!-- END About/Intro Menu -->
			</div>
			<!-- END GRID -->
		</div>
		<!-- END w3-content -->
	</div>

	<!-- Footer -->
	<footer class="w3-container w3-dark-grey" style="padding: 32px">
		<p class="w3-left w3-margin-left">
			Powered by <a href="https://www.w3schools.com/w3css/default.asp"
				target="_blank">Nailro</a>
		</p>
		<a href="#"
			class="w3-button w3-black w3-right w3-padding-large w3-margin-bottom"><i
			class="fa fa-arrow-up w3-margin-right"></i>To the top</a>

	</footer>

	<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>

</body>
</html>
