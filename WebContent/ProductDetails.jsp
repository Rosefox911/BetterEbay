<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="api.*, classes.*, java.sql.*, java.util.*, java.io.*, java.net.*, org.json.simple.parser.*, org.json.simple.*;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script>
function captureComment(itemid, title)
{
var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
    }
  }
var comment = document.getElementById("comment").value;
xmlhttp.open("GET","processComment.jsp?id=" + itemid + "&title=" + title + "&comment=" + comment,true);
xmlhttp.send();
}
</script>
<body>
<%
String id = request.getParameter("id");

String finderAPI = "http://open.api.ebay.com/shopping?callname=GetSingleItem&responseencoding=JSON&appid=JorgeDel-89b1-4723-8ee2-8812f73e42fe&siteid=0&version=515&ItemID=" + id;

URL url = new URL(finderAPI);
HttpURLConnection connection = (HttpURLConnection) url.openConnection();
connection.setRequestMethod("GET");
InputStream stream = connection.getInputStream();
InputStreamReader reader = new InputStreamReader(stream);
BufferedReader buffer = new BufferedReader(reader);
String line;
String json = "";
while((line = buffer.readLine()) != null) {
	json += line;
}
JSONParser parser = new JSONParser();
JSONObject root = (JSONObject) parser.parse(json);
JSONObject FirstItem = (JSONObject) root.get("Item");
String ItemID = FirstItem.get("ItemID").toString();
String EndTime = FirstItem.get("EndTime").toString();
String ItemURL = FirstItem.get("ViewItemURLForNaturalSearch").toString();
String ListingType = FirstItem.get("ListingType").toString();
String GalleryURL = FirstItem.get("GalleryURL").toString();
String PrimaryCategoryID = FirstItem.get("PrimaryCategoryID").toString();
String PrimaryCategoryName = FirstItem.get("PrimaryCategoryName").toString();
String ListingStatus = FirstItem.get("ListingStatus").toString();
String TimeLeft = FirstItem.get("TimeLeft").toString();
String Title = FirstItem.get("Title").toString();
String pictureurl = FirstItem.get("PictureURL").toString();
JSONObject ConvertedCurrentPriceList = (JSONObject) FirstItem.get("ConvertedCurrentPrice");
Double ConvertedCurrentPrice = (Double) ConvertedCurrentPriceList.get("Value");
%>
<h2>Item ID: <%=ItemID %></h2>
<h2>Item URL: <%=ItemURL %></h2>
<h2>Listing Type: <%=ListingType %></h2>
<h2>Picture: <img src = "<%=GalleryURL %>"></h2>
<h2>Price: <%= ConvertedCurrentPrice %></h2>

<input type=text id="comment" name="comment"> 
<input type="button" name = "like" value = "comment" onclick="captureComment('<%= ItemID %>', '<%= Title %>')">


		<div class="recentcomments">
		<h1>Recent Comments</h1>
		<% CommentDAO c = CommentDAO.getInstance();
		//System.out.println(ItemID);
		//System.out.println(Title);
		List<Comment> recentComments = c.recentCommentsForProduct(ItemID);
		%>
		
		<%
			for (Comment comment: recentComments) {
		%>
		
		<tr>
		<td><%=comment.getUser().getFirstname() %> Commented <%= comment.getContent()%></td>
		</tr>
		</br>
		</br>
		<%}%>
		</div>
</body>
</html>