<div id="content">
    <h3>
        ${exceptionInfo}
    </h3>

    <ul>
    <#list exceptionInfo.stackTrace as frame>
          <li>
             ${frame}
          </li>
    </#list>
    </ul>
</div>
