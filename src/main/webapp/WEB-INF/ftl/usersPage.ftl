<div id="content">
    <h4>
        User:
    </h4>
    <ul>
        <table>
            <thead>
            <tr bgcolor="gray">
                <td width="50">Id</td>
                <td width="50">Email</td>
                <td width="50">Name</td>
                <td width="50">Birthday</td>
            </tr>
            </thead>
            <tbody>
                <#list objectsList as obj>
                    <tr>
                    <td><a href="id/${obj.getId()}">${obj.getId()}</a></td>
                    <td><a>${obj.getEmail()}</a></td>
                    <td><a>${obj.getName()}</a></td>
                    <td><a>${obj.getBirthday()}</a></td>
                    <td>
                        <button onclick="location.href='edit/${obj.getId()}'" type="button">Edit</button>
                    </td>
                    <td>
                        <button onclick="location.href='delete/${obj.getId()}'" type="button">Delete</button>
                    </td>
                </#list>
            </tbody>
        </table>

        <br/>
        <button onclick="location.href='spring-course-1.0-SNAPSHOT/addUser'" type="button">New user</button>
        <br/>
    </ul>
</div>
