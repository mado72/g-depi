<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="depi" uri="/depi-tags" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table id="tabela_interna" class="sortable Funcionario Consulta">
	<thead>
		<tr>
		<th class="selecao center" style="width:5%">
			<s:text name="label.todos"/>
			<br/>
			<input type="checkbox" class="optionbutton checkTodos" ${chkDisabled}/>
		</th>
		<th class="center" style="width:25%">
			<s:text name="label.grid.departamento.siglaDepartamento"/>
		</th>
		<th class="nome" style="width:70%">
			<s:text name="label.grid.departamento.descricaoDepartamento"/>
		</th>
		</tr>
	</thead>
	<tbody class="lista">
	<c:forEach varStatus="status" var="item" items="${deptos}">
		<tr>
		<td class="selecao center">
			<input type="checkbox" class="optionbutton" name="siglaDepartamentos" value="<c:out value="${item.siglaDepartamento}"/>" ${chkDisabled}/>
		</td>
		<td class="center">
			${item.siglaDepartamento}
		</td>
		<td class="nome">${item.nomeDepartamento}</td>
		</tr>
	</c:forEach>
 	</tbody>
</table>
