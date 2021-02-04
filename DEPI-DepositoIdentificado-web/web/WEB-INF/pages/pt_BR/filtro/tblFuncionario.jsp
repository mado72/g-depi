<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="depi" uri="/depi-tags" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table id="tabela_interna" class="sortable Funcionario Consulta">
	<thead>
		<tr>
<c:choose>
<c:when test="${!matriculaNome}">
		<th class="selecao center" style="width:10%">
			<s:text name="label.todos"/>
			<br/>
			<input type="checkbox" class="optionbutton checkTodos" />
		</th>
		<th class="codigoUsuario center" style="width:15%">
			<s:text name="label.grid.usuario.codigoUsuario"/>
		</th>
		<th class="nome" style="width:40%">
			<s:text name="label.grid.usuario.nomeUsuario"/>
		</th>
		<th class="responsavel center" style="width:10%">
			<s:text name="label.grid.usuario.responsavelAtualizacao"/>
		</th>
		<th class="atualizacao center">
			<s:text name="label.grid.usuario.dataHoraAtualizacao"/>
		</th>
</c:when>
<c:otherwise>
		<th class="selecao center" style="width:15%">
			<s:text name="label.todos"/>
			<br/>
			<input type="checkbox" class="optionbutton checkTodos" />
		</th>
		<th class="codigoUsuario center" style="width:25%">
			<s:text name="label.grid.usuario.codigoUsuario"/>
		</th>
		<th class="nome" style="width:60%">
			<s:text name="label.grid.usuario.nomeUsuario"/>
		</th>
</c:otherwise>
</c:choose>
		</tr>
	</thead>
	<tbody class="lista">
 	<s:iterator value="colecaoDados" var="item" status="status">
		<tr>
<c:choose>
<c:when test="${!matriculaNome}">
		<td class="selecao center">
			<input type="checkbox" class="optionbutton" name="codigo" value="<c:out value="${item.codigoUsuario}"/>"/>
		</td>
		<td class="center">
			${item.codigoUsuario}
		</td>
		<td class="nome">${item.nomeUsuario}</td>
		<td class="responsavel center">${item.responsavelAtualizacao}</td>
		<td class="atualizacao center"><fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "medium" value="${item.dataUltimaAtualizacao}"/></td>
</c:when>
<c:otherwise>
		<td class="selecao center">
			<input type="checkbox" class="optionbutton" name="codigo" value="<c:out value="${item.codigoUsuario}"/>"/>
		</td>
		<td class="center">
			${item.codigoUsuario}
		</td>
		<td class="nome">${item.nomeUsuario}</td>
</c:otherwise>
</c:choose>
		</tr>
	</s:iterator>
 	</tbody>
</table>
