# ValleTech - Sistema de Gestión de Productos

Aplicación Desktop Java con patrón **MVC** para la gestión de productos.

---

## 📁 Estructura del Proyecto

```
ValleTech/
├── src/
│   ├── Main.java                    ← Punto de entrada
│   ├── model/
│   │   ├── Usuario.java             ← Entidad usuario
│   │   ├── UsuarioDAO.java          ← Acceso a datos usuario
│   │   ├── Producto.java            ← Entidad producto
│   │   └── ProductoDAO.java         ← CRUD productos (PreparedStatement)
│   ├── view/
│   │   ├── LoginView.java           ← Pantalla de inicio de sesión
│   │   ├── DashboardView.java       ← Menú principal
│   │   ├── ProductoView.java        ← CRUD UI con JTable
│   │   └── ReporteView.java         ← Módulo de reportes
│   ├── controller/
│   │   ├── LoginController.java     ← Lógica de autenticación
│   │   ├── ProductoController.java  ← Lógica CRUD productos
│   │   └── ReporteController.java   ← Lógica reportes JasperReports
│   └── util/
│       └── Conexion.java            ← Singleton de conexión MySQL
├── lib/
│   ├── mysql-connector-j-8.x.jar   ← Descargar de MySQL
│   ├── jasperreports-x.x.x.jar     ← JasperReports
│   └── (dependencias de jasper...)
├── reports/
│   └── ReporteProductos.jrxml      ← Plantilla del reporte
└── sql/
    └── valletech.sql               ← Script de base de datos
```

---

## ⚙️ Configuración Previa

### 1. Base de Datos MySQL
```sql
-- Ejecutar el archivo sql/valletech.sql
-- Usuario por defecto: admin / admin123
```

### 2. Conexión (util/Conexion.java)
Modificar si cambia usuario/contraseña de MySQL:
```java
private static final String URL      = "jdbc:mysql://localhost:3306/valletech";
private static final String USUARIO  = "root";
private static final String CONTRASENA = "";    // ← tu contraseña
```

### 3. Librerías en /lib
Descargar y agregar al classpath:
- **MySQL Connector/J**: https://dev.mysql.com/downloads/connector/j/
- **JasperReports**: https://community.jaspersoft.com/download-jasperreports-library/

Dependencias JasperReports necesarias:
- `jasperreports-x.x.x.jar`
- `commons-beanutils.jar`
- `commons-collections.jar`
- `commons-digester.jar`
- `commons-logging.jar`
- `itext-x.x.x.jar` (para exportar PDF)

### 4. Reporte JRXML
Copiar `reports/ReporteProductos.jrxml` dentro del classpath
(en NetBeans: dentro del paquete `reports` en `src/`).

---

## 🚀 Compilar y Ejecutar

### Con NetBeans / IntelliJ
1. Crear proyecto Java (no Maven).
2. Agregar los `.jar` de `/lib` como bibliotecas.
3. Importar los archivos de `src/`.
4. Configurar `Main.java` como clase principal.
5. Run ▶️

### Con línea de comandos
```bash
# Compilar
javac -cp "lib/*" -d out $(find src -name "*.java")

# Ejecutar
java -cp "out:lib/*" Main
```

---

## 📋 Funcionalidades

| Módulo            | Descripción                                          |
|-------------------|------------------------------------------------------|
| **Login**         | Autenticación con MySQL, mensajes de éxito/error     |
| **Dashboard**     | Menú con Gestión de Productos, Reportes, Cerrar Sesión |
| **CRUD Productos** | Registrar, Modificar, Eliminar, Buscar, Listar en JTable |
| **Reporte**       | JasperReports con datos de la tabla `productos`      |
| **Exportar PDF**  | Genera `ReporteProductos.pdf` en el directorio raíz  |

---

## 🎓 Parte Teórica

1. **¿Cuál es la función del patrón MVC?**
   Separar la aplicación en tres capas: Model (datos y lógica de negocio), View (interfaz gráfica) y Controller (intermediario), facilitando el mantenimiento y la escalabilidad.

2. **¿Qué responsabilidad tiene el Controller?**
   Recibir eventos de la Vista, invocar métodos del Modelo y actualizar la Vista con los resultados. Actúa como puente entre las otras dos capas.

3. **¿Cuál es la diferencia entre PreparedStatement y Statement?**
   `PreparedStatement` precompila la consulta SQL, previene inyección SQL y mejora el rendimiento en consultas repetidas. `Statement` concatena cadenas, lo que es inseguro y menos eficiente.

4. **¿Qué ventajas ofrece JasperReports?**
   Generación de reportes profesionales en múltiples formatos (PDF, Excel, HTML), diseño visual con plantillas JRXML, soporte para gráficos, imágenes y fuentes de datos variadas.

5. **¿Por qué es importante separar la lógica de negocio de la interfaz gráfica?**
   Para lograr código más mantenible, testeable y reutilizable. Permite modificar la UI sin afectar la lógica, y viceversa. Facilita el trabajo en equipo y la escalabilidad del sistema.
