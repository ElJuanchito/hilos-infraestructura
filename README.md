# Hilos y Concurrencia

Un ejemplo común para la aplicación de hilos, es un servicio de atención al cliente en una EPS, existiendo varios asesores que al mismo tiempo atiende a un cliente cada uno, modelando esto como un proceso en paralelo, siendo un hilo. Su problema de concurrencia sucede cuando dos asesores realizan operaciones críticas al momento de atender usuarios, ambos accediendo a una misma lista de citas para realizar sus operaciones.


<h1 align="center">Problema</h1>
El problema de concurrencia se presenta en el servicio de la EPS, este puede ser solucionado mediante la sincronización, utilizando el bloqueo de recurso de manera temporal, sabiendo que, por medio de variables condicionales el primer hilo en acceder al recurso, toma el control y lo bloquea para que así, los demás lo observan como ocupado y no puedan acceder a él, este servicio está disponible para el primer hilo por un tiempo limitado.

<h1 align="center">Solución</h1>
Esta solución al problema de concurrencia se puede ver en 3 diferentes lenguajes

<h2 align="center">Java</h2>

<div align="center">
  <table width="10">
    <tr>
      <td>
        <a align="center" href="java">
          <div>Java</div>
       </a>
      </td>
      <td>
       <div align="center"> 
         <a href="java">
          <img src="https://skillicons.dev/icons?i=java">
        </a></div>
      </td>
    </tr>
    <tr>
      <td colspan="2">
      La solución en java se puede ver usando hilos y la palabra reservada synchronized para los métodos críticos:
        </td>
    </tr>
  </table>
</div>


<h2 align="center">Python</h2>

<div align="center">
  <table>
    <tr>
      <td>
        <a align="center" href="java">
          <div>Python</div>
       </a>
      </td>
      <td>
       <div align="center"> 
         <a href="python">
          <img src="https://skillicons.dev/icons?i=python">
        </a></div>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        La solución en python se puede ver usando locks que no dejan que bloquean la ejecución de otros hilos en una situación crítica.
      </td>
    </tr>
  </table>
</div>

<div align="center">
  <table>
    <tr>
      <td>
        <a align="center" href="c++">
          <div>C++</div>
       </a>
      </td>
      <td>
       <div align="center"> 
         <a href="c++">
          <img src="https://skillicons.dev/icons?i=cpp">
        </a></div>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        La solución en C++ se ve en la implementación de mutex que no permiten la ejecución de otros hilos en un momento crítico.
      </td>
    </tr>
  </table>
</div>
