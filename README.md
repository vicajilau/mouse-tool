# mouse-tool
Tool to activate and deactivate the touchpad by keyboard shortcut in GNU/Linux

# Dependencias:
xinput, default-jre

Puedes resolver las dependecias mediante:

"sudo apt-get install xinput"
"sudo apt-get install default-jre"

# A continuación, para instalar:
1º. Descomprimir fichero.
2º. Ejecutar el install desde un terminal: "sudo bash ./install"
3º. Agregar dos atajo de teclado personalizados (uno para desactivar y otro para activar) 
Para desactivar el touchad
Nombre: Desactivar touchpad
Orden: java -jar /opt/mouse-tool/mouse-tool.jar -d
Combinaciones de tecla: Ctrl + D

Para activar el touchad
Nombre: Activar touchpad
Orden: java -jar /opt/mouse-tool/mouse-tool.jar -a


# COMO CREAR ATAJOS DE TECLADO EN LOS DISTINTOS ENTORNOS DE ESCRITORIO

Cinnamon
1º. Menú - Configuración del sistema - Teclado
2º. Atajos de teclado, y en el menú de la izquierda 'Atajos personalizados'
3º. Pinchamos en 'Añadir atajo personalizado'
    Nombre: Desactivar Touchpad
    Orden: java -jar /opt/mouse-tool/mouse-tool.jar -d
4º. En el cuadro de 'Combinaciones de teclas' hacemos doble click sobre un 'sin asignar'
    Y utilizamos la combincación de teclas que queremos a poner
    Ejemplo: Ctrl + d
5º. Repitimps los pasos 3º y 4º para Activar el touchpad
3ºB Pinchamos en 'Añadir atajo personalizado'
    Nombre: Activar Touchpad
    Orden: java -jar /opt/mouse-tool/mouse-tool.jar -a
4ºB En el cuadro de 'Combinaciones de teclas' hacemos doble click sobre un 'sin asignar'
    Y utilizamos la combincación de teclas que queremos a poner
    Ejemplo: Ctrl + a

# AGRADECIMIENTOS
David Ruiz
