<h1>MÍNIMO 1</h1>

El repositorio contiene la propuesta de solución al examen del mínimo 1 de la asignatura de DSA de la EETAC (curso 2023-2024).
En dicho examen, se pedía la creación de un juego para la EETAC que permita promocionar la escuela.

Para implementar la solución he creado 3 clases de Java: <b>Usuario.java</b>, <b>Partida.java</b> y <b>Juego.java</b>:

<ul>
    <li><b>Usuario.java</b> --- contiene los siguientes atributos:</li>
    <ul>
        <li>
            <b>String id</b> -- identidad del usuario que será de tipo String.
        </li>
        <li>
            <b>String mail</b> -- mail del usuario que será de tipo String.
        </li>
        <li>
            <b>String name</b> -- nombre del usuario que será de tipo String.
        </li>
        <li>
            <b>String pass</b> -- contraseña del usuario que será de tipo String.
        </li>
        <li>
            <b>HashMap{String,Partida} partidas</b> -- listado de partidas del usuario que será de tipo HashMap.
        </li>
    </ul>
    <li><b>Partida.java</b> --- contiene los siguientes atributos:</li>
    <ul>
        <li>
            <b>String id</b> -- identidad de la partida que será de tipo String.
        </li>
        <li>
            <b>HashMap{String,Juego} juegos</b> -- relación de las partidas con el juego.
        </li>
        <li>
            <b>boolean completado</b>b> -- variable para saber si hemos finalizado la partida o no de tipo booleana.
        </li>
    </ul>
    <li><b>Juego.java</b> --- contiene los siguientes atributos:</li>
    <ul>
        <li>
            <b>String idJuego</b> -- identidad del juego que será de tipo String.
        </li>
        <li>
            <b>String descripcion</b> -- descripción del juego de tipo String.
        </li>
        <li>
            <b>int nivelesTotales</b>b> -- cantidad de niveles que tiene el juego de tipo int.
        </li>
    </ul>
</ul>

<h3>PARTE I DEL EXAMEN</h3><i>IMPLEMENTADA AL 80 %</I>
<ul>
    <li>Propuesta de atributos realizada.</li>
    <li>Interfaz y clases java definidas y completamente funcionales.</li>
    <li>Implementación (parcial) de la fachada. Estrucutras de datos definidas y trazas creadas.</li>
    <li>Implementado y funcional el test con las 4 operaciones requeridas.</li>
</ul>

<h3>PARTE II DEL EXAMEN</h3><i>IMPLEMENTADA AL 100 %</I>

<p>
    Definido y en funcionamiento el servicio REST que permite realizar las operaciones descritas en la primera parte.
</p>