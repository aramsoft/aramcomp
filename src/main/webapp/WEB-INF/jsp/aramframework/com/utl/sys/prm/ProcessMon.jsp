<%@ page language="java"
   import= "java.io.*,
   java.util.*"
   contentType="text/html; charset=euc-kr" session="false"
%>

<html lang="ko">
<%

    String command = "netstat -an";  // <---- ������ ����ɾ�
    int lineCount = 0;
    String line="";

    Runtime rt = Runtime.getRuntime();
    Process ps = null;

    try{
      	ps = rt.exec(command);

      	BufferedReader br =
            new BufferedReader(
                new InputStreamReader(
                    new SequenceInputStream(ps.getInputStream(), ps.getErrorStream())));



      	while((line = br.readLine()) != null){
%>
    	<%=line%><br> <!-- ��� ȭ�鿡 �Ѹ���... -->
<%
    	}
      	br.close();

   	}catch(Exception e){
	 	//e.printStackTrace();
     	throw new RuntimeException(e);	// 2011.10.10 �������� �ļ���ġ
   	}
%>
</html>
