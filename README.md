# 🍽️ Sistema de Gestión de Restaurante - Alcalá v1.0

[![Java](https://img.shields.io/badge/Built%20with-Java-blue?style=for-the-badge&logo=java)](https://www.java.com/)
[![Versión](https://img.shields.io/badge/Versión-1.0-brightgreen?style=for-the-badge)]()
[![Estado](https://img.shields.io/badge/Estado-Estable-blueviolet?style=for-the-badge)]()

## 📝 Descripción General

Este proyecto desarrollado en **Java con interfaz gráfica (JPanel)** representa un sistema integral para la gestión de operaciones dentro de un restaurante, enfocado en el control detallado de mesas, pedidos, facturación y caja. Su diseño permite tanto a administradores como a usuarios operar de manera eficiente mediante un sistema de login con roles diferenciados.

## 🔐 Funcionalidades Principales

- **Inicio de sesión** con roles diferenciados: Administrador y Usuario.
- **Control y seguimiento de pedidos** asignados a cada mesa del restaurante.
- **Inventario de productos** con capacidad de modificar existencias.
- **Generación automática de facturas** para cada transacción.
- **Gestión de caja diaria**, con registro de entradas y salidas.
- **Pago de nómina**, incluyendo descuento automático del monto desde caja.
- **Cierre de caja** con reporte consolidado de facturación por método de pago.

## 🧾 Módulos Implementados

1. **Login**
   - Sistema de autenticación para acceso seguro.
   - Separación de vistas según rol.

2. **Pedidos por Mesa**
   - Registro individual de pedidos por número de mesa.
   - Cálculo automático del total por cliente.

3. **Facturación**
   - Generación de facturas con desglose de productos.
   - Soporte para distintos métodos de pago.

4. **Caja**
   - Registro de ingresos y egresos.
   - Control de caja en tiempo real.
   - Cierre de caja con facturas organizadas por método de pago.

5. **Nómina**
   - Registro y pago de empleados.
   - Descuento del total desde la caja del restaurante.

## 🧪 Requisitos Técnicos

- Java 8 o superior.
- Sistema operativo compatible con `.jar` (Windows, Linux, macOS).
- IDE recomendado: VS, NetBeans, IntelliJ o Eclipse.

## 🚀 Ejecución del Proyecto

1. Asegúrate de tener Java instalado.
2. Descarga el archivo `Alcala.V1.0.jar`.
3. Ejecútalo con doble clic o mediante terminal con:

```bash
java -jar Alcala.V1.0.jar
```

## 📦 Estructura del Sistema

```text
Alcala/
├── Login/
│   ├── AdminLogin.java
│   └── UserLogin.java
├── Caja/
│   ├── RegistroCaja.java
│   └── CierreCaja.java
├── Inventario/
│   └── InventarioProductos.java
├── Pedidos/
│   ├── Mesa1.java ... MesaN.java
│   └── GenerarFactura.java
├── Nomina/
│   └── PagoEmpleados.java
└── Main.java
```

## 📄 Licencia

Este proyecto se encuentra bajo la Licencia GPL 3.0. Puedes usarlo, modificarlo y distribuirlo siempre que mantengas los mismos términos de licencia.

## 👨‍🎓 Autor

Desarrollado por **Sebastián Vega**  
📧 *Sebastian.vegar2015@gmail.com*  
🔗 [LinkedIn - Johan Sebastián Vega Ruiz](https://www.linkedin.com/in/johan-sebastian-vega-ruiz-b1292011b/)

---


© 2025 — Sebastian Vega
