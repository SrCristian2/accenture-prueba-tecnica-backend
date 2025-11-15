# Script para ejecutar la aplicación de Franquicias

Write-Host "=== Starting Franchise Application ===" -ForegroundColor Green
Write-Host ""

# Verificar si Java está instalado
try {
    $javaVersion = java -version 2>&1 | Select-String "version"
    Write-Host "✓ Java found: $javaVersion" -ForegroundColor Green
} catch {
    Write-Host "✗ Java is not installed or not in PATH" -ForegroundColor Red
    exit 1
}

# Verificar si Maven está instalado
$useMvnw = $false
try {
    $mavenVersion = mvn -version 2>&1 | Select-String "Apache Maven"
    Write-Host "✓ Maven found: $mavenVersion" -ForegroundColor Green
} catch {
    Write-Host "! Maven not found, using Maven Wrapper" -ForegroundColor Yellow
    $useMvnw = $true
}

Write-Host ""
Write-Host "=== Compiling the project ===" -ForegroundColor Cyan

if ($useMvnw) {
    .\mvnw.cmd clean package -DskipTests
} else {
    mvn clean package -DskipTests
}

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "✓ Compilation successful" -ForegroundColor Green
    Write-Host ""
    Write-Host "=== Starting the application ===" -ForegroundColor Cyan
    Write-Host "The application will be available at: http://localhost:8080" -ForegroundColor Yellow
    Write-Host "Press Ctrl+C to stop the application" -ForegroundColor Yellow
    Write-Host ""
    
    if ($useMvnw) {
        .\mvnw.cmd spring-boot:run
    } else {
        mvn spring-boot:run
    }
} else {
    Write-Host ""
    Write-Host "✗ Compilation error" -ForegroundColor Red
    exit 1
}
