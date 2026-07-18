# FinanceApp
Aplicación web de finanzas personales para gestionar ingresos, gastos y deudas de forma sencilla y visual.

# Características principales
Autenticación de usuarios (registro, login, cambio de contraseña).

Gestión de gastos: añadir, editar, eliminar y buscar gastos por categoría o descripción.

Categorías personalizables: crear nuevas categorías sobre la marcha.

Gestión de ingresos: registrar ingresos con fuente y fecha.
	
Gestión de deudas: añadir deudas con cálculo automático de pago mensual, intereses y seguimiento de progreso.

Dashboard con estadísticas: balance total, ingresos, gastos, deuda y gráfico circular de distribución de gastos.

Selector de colores temáticos: personaliza el color principal de la interfaz.

# Tecnologías utilizadas
Backend
Java 17

Spring Boot 3.4.x

Spring Data JPA (Hibernate)

Base de datos H2 (en memoria, para desarrollo)

Maven (gestión de dependencias)

Frontend
HTML5, CSS3 (diseño modular con variables CSS)

JavaScript (Vanilla JS, Fetch API)

Chart.js (gráficos)

# Requisitos previos
JDK 17 o superior

Maven (opcional, puedes usar el wrapper mvnw)

Navegador web moderno

# Instalación y ejecución
1. Clonar el repositorio
   git clone https://github.com/tu-usuario/financeapp.git
	 cd financeapp

2. Ejecutar Backend
    Con Maven instalado: 
		mvn spring-boot:run
		
    O con el wrapper (Linux/Mac): 
		./mvnw spring-boot:run
		
    O en Windows: 
		mvnw.cmd spring-boot:run
	  El servidor arrancará en http://localhost:8080.

3. Acceder al frontend
		Abre en tu navegador el archivo login.html directamente

# Base de Datos
Base de datos
La aplicación usa H2 en memoria con las siguientes tablas:

users (id, name, nickname, password)

category (id, name)

expense (id, amount, date, description, category_id, user_id)

income (id, amount, date, source)

debt (id, name, total_amount, monthly_payment, remaining_months, total_months, interest_rate, start_date, end_date, next_payment_date, active, remaining_amount)

payment (id, amount, payment_date, month_number, debt_id)

# Autenticación
Registro: POST /users con { "name": "John", "nickname": "johnny", "password": "12345" }

Login: POST /users/login con { "name": "johnny", "password": "12345" }
Devuelve el usuario (sin la contraseña) y se guarda en localStorage con la clave user.

Nota de seguridad: Las contraseñas se almacenan en texto plano para simplificar el proyecto. No uses esta configuración en producción real.

# Estructura del frontend
login.html – Página de inicio de sesión.

register.html – Registro de nuevos usuarios.

dashboard.html – Panel principal (gastos, ingresos, deudas).

profile.html – Perfil de usuario (cambio de contraseña).

css/ – Hojas de estilo modulares (global.css, login.css, dashboard.css, components.css, themes.css, profile.css).

# Personalización de colores
En el dashboard, haz clic en el círculo de color (esquina superior derecha) para abrir el selector. Puedes elegir un color de la rueda o de los predefinidos. El cambio se aplica a toda la interfaz y se guarda en localStorage para futuras visitas.

