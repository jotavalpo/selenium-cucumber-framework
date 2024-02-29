package pageObjects;

import driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants_Vars;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.List;


public class Base_PO {

    public Base_PO() {
        PageFactory.initElements(getDriver(), this);
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    /**
     * Navega hacia una url especifica
     *
     * @param url - Url a la que se quiere navegar
     **/
    public void navigateToUrl (String url)  {
        getDriver().get(url);
    }


    /**
     * Accion de mandar valores a un elemento By
     *
     * @param by - selector de cualquier tipo (Xpath, ClassName, etc...)
     * @param textToType - texto que se desea escribir
     **/

    public void sendKeys(By by, String textToType) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants_Vars.DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(by)).sendKeys(textToType);
    }

    /**
     * Accion de mandar valores a un WebElement
     *
     * @param element - Webelement con el que se quiere interactuar
     * @param textToType - texto que se desea escribir
     **/
    public void sendKeys(WebElement element, String textToType) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants_Vars.DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(textToType);
    }
    public void clearAndsendKeys(WebElement element, String textToType) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants_Vars.DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
        element.sendKeys(textToType);
    }

    /**
     * Espera por un elemento hasta que sea clickeable, y luego hace click
     *
     * @param by - selector de cualquier tipo (Xpath, ClassName, etc...)
     **/
    public void waitForWebElementAndClick(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants_Vars.DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    /**
     * Espera por un elemento hasta que sea clickeable, y luego hace click
     *
     * @param element - Webelement al que haremos click
     **/
    public void  waitForWebElementAndClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants_Vars.DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    /**
     * Espera por un elemento hasta que sea clickeable
     *
     * @param timeoutInSeconds tiempo máximo en segundos que se esperará por el elemento
     * @param element          elemento que se desea esperar a que sea clickeable
     * @return
     */
    public void waitForElementClickeable (WebElement element, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementClickeable (By by, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * Espera por un elemento hasta que sea visible, si no lo encuentra, lanza una excepción que si no se controla
     * (try/catch por ejemplo) detendrá la ejecución del test
     *
     * @param timeoutInSeconds tiempo máximo en segundos que se esperará por el elemento
     * @param element          elemento que se desea encontrar
     **/
    public void waitForElementVisibleAndContinue (WebElement element, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Espera por un elemento hasta que sea visible, si no lo encuentra, lanza una excepción que si no se controla
     * (try/catch por ejemplo) detendrá la ejecución del test
     *
     * @param timeoutInSeconds tiempo máximo en segundos que se esperará por el elemento
     * @param element          elemento que se desea encontrar
     **/
    public void waitForElementNotVisible (WebElement element, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Retorna si un elemento es visible en algún momento dentro de los segundos indicados
     *
     * @param timeoutInSeconds tiempo máximo en segundos que se esperará por el elemento para que sea visible
     * @param element          elemento que se desea encontrar
     * @return true si el elemento es visible, false en caso contrario
     **/
    public boolean isVisibleElement (WebElement element, long timeoutInSeconds) {
        boolean isVisible = false;
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
        isVisible = wait.until(ExpectedConditions.visibilityOf(element)) != null;
        return isVisible;
    }


    /**
     * Retorna si un elemento es visible en algún momento dentro de los segundos indicados
     *
     * @param element - elemento que se desea encontrar
     * @param intentos - intenta encontrar un elemento n veces
     * @return true si el elemento se encuentra, false en caso contrario
     **/
    public boolean existElement (By element, long intentos) throws InterruptedException {
        boolean isPresent = false;

        for (int i = 0; i < intentos; i++) {
            try {
                if (getDriver().findElement(element) != null) {
                    isPresent = true;
                    break;
                }
            } catch (Exception e) {
                Thread.sleep(50);
            }
        }
        return isPresent;
    }

    public void waitForAlertAndAccept () {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants_Vars.DEFAULT_TIMEOUT));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    public void esperaImplicita (int tiempo) throws InterruptedException {
        Thread.sleep(tiempo);
    }

    /**
     * * Cambiar el foco de ventana web
     *
     * @param cambiarSoloUna En caso de haber mas de dos ventana y solo querer cambiar a la siguiente, true
     **/
    public  void cambiarVentanaWeb(boolean cambiarSoloUna) {
        String MainWindow = getDriver().getWindowHandle();
        Set<String> s1 = getDriver().getWindowHandles();
        Iterator<String> i1 = s1.iterator();

        while (i1.hasNext()) {
            String ChildWindow = i1.next();
            if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
                getDriver().switchTo().window(ChildWindow);
                if (cambiarSoloUna)
                    break;
            }
        }
    }

    /**
     * Cerrar ventana web, solo cierra ventana sin detener driver
     */
    public void cerrarVentanaWeb() {
        String MainWindow = getDriver().getWindowHandle();
        Set<String> s1 = getDriver().getWindowHandles();
        Iterator<String> i1 = s1.iterator();

        while (i1.hasNext()) {
            String ChildWindow = i1.next();
            if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
                getDriver().close();
                getDriver().switchTo().window(ChildWindow);
            }
        }
    }

    /**
     * Sroll hasta final de pagina
     */
    public void scrollFinalVentana() {
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,500)");
    }

    /**
     * scrollElement, realiza scroll de pagina en donde se encuentra objeto
     *
     * @param element WebElement donde se realizara el Scroll
     */
    public void scrollElement(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollElement(By by) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", by);
    }

    public void scrollDownPage() {
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0, 1000);");
    }

    /**
     * scrollClickElement, realiza scroll y ademas click al elemento de la pagina
     *
     * @param element WebElement donde realizara Scroll y hara click
     */
    public void scrollClickElement(WebElement element) {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * obtenerTextoNovisible, retorna el texto de un elemento que no esta visible como por ejemplo en un div donde hay lista de objetos y estos no se visualizan todos
     *
     * @param element WebElement que se obtendra el texto
     * @return String no visible
     */
    public String obtenerTextoNoVisible(WebElement element) {
        String texto = "";
        try {
            texto = element.getText().trim();
            texto = (String) ((JavascriptExecutor) getDriver()).executeScript("return arguments[0].innerText", element);
        } catch (Exception e) {
            texto = "";
        }
        return texto;
    }

    public String obtenerValorElemento(WebElement element) {
        String texto = "";
        try {
            texto = (String) ((JavascriptExecutor) getDriver()).executeScript("return arguments[0].value", element);
        } catch (Exception e) {
            texto = "";
        }
        return texto;
    }

    /**
     * ingresarCaracteresEspecialesID, en algunos navegadores no ingresa caracteres especiales como @ de forma correcta, este metodo mitiga ese conflicto
     *
     * @param element          WebElement al cual se ingresara frase o caracter especial
     * @param id               Identificador ID del elemento al que se ingresara caracter
     * @param caracterEspecial Frase o caracter especial a ingresar
     */
    public void ingresarCaracteresEspecialesID(WebElement element, String id, String caracterEspecial) {
        try {
            ((JavascriptExecutor) getDriver()).executeScript(String.format("document.getElementById(\"" + id + "\").value=\"" + caracterEspecial + "\";", element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean unloadWebElementByClass(WebElement webElement, int segundos) {
        Boolean elementExist = isElementPresent(webElement);
        int conTiempo = 0;
        String claseTag = "";
        if (elementExist)
            claseTag = webElement.getAttribute("class");
        while (elementExist && conTiempo <= segundos) {
            elementExist = (Boolean) ((JavascriptExecutor) getDriver()).executeScript("var elemento = document.getElementsByClassName('" + claseTag + "');return (elemento.length == 0)?false:true;");
            try {
                getDriver().wait(2 * 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            conTiempo = conTiempo + 2;
        }
        return elementExist;
    }

    /**
     * Comprobar si despliega alert y cerrar este mismo
     *
     * @param aceptar - true para aceptar el valor, falso para lo contrario
     */
    public void checkAlert(boolean aceptar) {
        try {

            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            if (getDriver().switchTo().alert() != null) {
                Alert alert = getDriver().switchTo().alert();
                if (aceptar)
                    alert.accept();
                else
                    alert.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Comprobar si despliega alert, obtiene mensaje de este y luego lo cierra
     */
    public String textoAlert() {
        String texto = "";
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            if (getDriver().switchTo().alert() != null) {
                Alert alert = getDriver().switchTo().alert();
                texto = alert.getText().trim();
                alert.accept();
            }
        } catch (Exception e) {
            texto = "";
            e.printStackTrace();
        }
        return texto;
    }

    /**
     * Cerrar ventana WebDriver
     */
    public void cerrarVentana() {
        getDriver().close();
    }

    /**
     * Cerrar proceso WebDriver
     */
    public void cerrarDriver() {
        getDriver().quit();
    }

    /**
     * Volver atras
     */
    public void volver() {
        getDriver().navigate().back();
    }

    /**
     * esperaElementoSegundos
     * Genera una pausa explicita hasta que el elemento dado es encontrado.
     *
     * @param webElement Elemento a esperar.
     * @param segundos   (int) Valor de tiempo en segundos a esperar.
     * @return {@link Boolean} Retorna un valor <b>verdadero</b> si el elemento es encontrado dentro del tiempo estipulado, de lo contrario retorna un valor <b>falso</b>.
     **/
    public boolean esperaElementoSegundos(WebElement webElement, int segundos) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(segundos));
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     *  esperaElementoSegundos
     *  Genera una pausa explicita hasta que lista de elementos dados sean encontrados.
     * @param listElement Lista de elementos a esperar.
     * @param segundos    (int) Valor de tiempo en segundos a esperar.
     * @return {@link Boolean} Retorna un valor <b>verdadero</b> si el elemento es encontrado dentro del tiempo estipulado, de lo contrario retorna un valor <b>falso</b>.
     **/
    public boolean esperaElementosSegundos(List<WebElement> listElement, int segundos) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(segundos));
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(listElement));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * <b>Nombre:</b> notEsperaElementoSegundos</br>
     * </br>
     * <b>Description:</b> Genera una pausa explicita hasta que el elemento dado desaparece.
     *
     * @param webElement Elemento a esperar.
     * @param segundos (int) Valor de tiempo en segundos a esperar.
     * @return {@link Boolean} Retorna un valor <b>verdadero</b> si el elemento es encontrado dentro del tiempo estipulado, de lo contrario retorna un valor <b>falso</b>.
     **/
    public boolean notEsperaElementoSegundos(WebElement webElement, int segundos) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(segundos));
        if (isElementPresent(webElement)) {
            try {
                wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(webElement)));
                return true;
            } catch (TimeoutException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * esperaElementoException
     * Genera una pausa explicita hasta que el elemento dado es encontrado.
     *
     * @param webElement Elemento a esperar.
     * @param segundos (int) Valor de tiempo en segundos a esperar.
     **/
    public void esperaElementoException(WebElement webElement, int segundos) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(segundos));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     *  isElementPresent
     *  Verifica la existencia de un elemento
     *
     * @param webElement Objeto de tipo WebElement a buscar
     * @return {@link Boolean} Retorna <b>True</b> si el elemento es encontrado, de lo contrario retorna <b>False</b>
     **/
    public boolean isElementPresent(WebElement webElement) {
        boolean resp = false;
        try {
            resp = webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            resp = false;
        }
        return resp;
    }

    /**
     * isClickable</br>
     *
     * Comprueba si elemento es posible hacerle click
     *
     * @param element WebElement a verificar
     * @return boolean
     **/
    public boolean isClickable(long timeout, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean existWebElement(WebElement elementoLista, By element) throws InterruptedException {
        boolean isPresent = false;
        try {
            if (elementoLista.findElement(element) != null) {
                isPresent = true;
            }
        } catch (Exception e) {
            Thread.sleep(1000);
        }

        return isPresent;
    }

    public WebElement getParentNode(WebElement element) {
        WebElement obj = null;
        try {
            obj = (WebElement) ((JavascriptExecutor) getDriver()).executeScript("return arguments[0].parentNode;", element);
        } catch (Exception e) {
            obj = null;
        }
        return obj;
    }

    /**
     * textoAlertBuscador Devuelve el texto de las alertas que aparecen en la ejecución actual, cierra el mensaje.
     *
     * @return t
     */
    public String textoAlertBuscador() {
        String texto = "";
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants_Vars.DEFAULT_TIMEOUT));

            wait.until(ExpectedConditions.alertIsPresent());
            if (getDriver().switchTo().alert() != null) {
                Alert alert = getDriver().switchTo().alert();
                texto = alert.getText().trim();
                alert.accept();
            }
        } catch (Exception e) {
            texto = "";
            e.printStackTrace();
        }
        return texto;
    }

    /**
     * mouseOver, realiza mouOvera elemento
     *
     * @param element WebElement donde realizara mouseover
     */
    public void mouseOverObject(WebElement element) {
        try {
            String javaScript = "var evObj = document.createEvent('MouseEvents');" +
                    "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                    "arguments[0].dispatchEvent(evObj);";
            ((JavascriptExecutor) getDriver()).executeScript(javaScript, element);
            System.out.println("realizo mouseOverss");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void highlightElement(By by) throws Exception {
        try {
            WebElement element = getDriver().findElement(by);
            scrollElement(element);
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void highlightElement(WebElement element) throws Exception {
        try {
            scrollElement(element);
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void acceptAlert() {

        try {

            final Alert alert = getDriver().switchTo().alert();
            alert.accept();

        } catch (Exception e) {
            System.out.println("Close Alert: " + e.getMessage());
        }
    }

    /**
     * Cancelar alerta.
     */
    public void dismissAlert() {

        try {
            final Alert alert = getDriver().switchTo().alert();
            alert.dismiss();

        } catch (Exception e) {
            System.out.println("Close Alert: " + e.getMessage());
        }
    }

    /**
     * Scroll en la ventana en uso.
     *
     * @param x - Cuantos pixeles a mover hacia izquierda o derecha. Un valor negativo es izquierda.
     * @param y - Cuantos pixeles a mover hacia arriba o abajo. Un valor negativo es arriba.
     */
    public void scroll(final int x, final int y) {
        final JavascriptExecutor js = (JavascriptExecutor) getDriver();
        for (int i = 0; i <= x; i = i + 50) {
            js.executeScript("scroll(" + i + ",0)");
        }
        for (int j = 0; j <= y; j = j + 50) {
            js.executeScript("scroll(0," + j + ")");
        }
    }

    /**
     * @param xpath - Xpath que tienen TODOS los elementos que se buscaran
     * @return - Retorna un listado de elementos (WebElement) para ser utilizados
     * @throws Exception - Bota la ejecucion si no encuentra elementos, se debe utilizar con try catch para capturar el error y continuar la ejecución
     */
    public List<WebElement> getAllTestObjectsWithXpath(String xpath) throws Exception {
        WebDriver driver = getDriver();
        List<WebElement> listofelements = driver.findElements(By.xpath(xpath));
        return listofelements;

    }

    /*
     * Ingresa una cadena en un elemento calendario
     */
    public void ingresaCalendario(WebElement element, String fecha) {

        scrollElement(element);
        getDriver().findElement((By) element).sendKeys(fecha);
        getDriver().findElement((By) element).sendKeys(Keys.chord(Keys.ENTER));

    }

    /**
     * MÉTODOS PARA USAR EL EXPORTADOR PERSONALIZADO CON JAVASCRIPT
     *
     * Descripción: permite seleccionar campos para exportador pasados en una lista
     * @param
     * @throws Exception
     */
    public boolean JSscript(String selector, String id, String parametro) throws InterruptedException {

        waitForPageLoad();
        WebElement esperaParametro = getDriver().findElement(By.xpath(parametro));
        esperaParametro.wait(10);
        esperaParametro.click();
        esperaParametro.wait(10);
        esperaParametro.click();

        WebElement esperaSelector = getDriver().findElement(By.cssSelector(selector));

        JavascriptExecutor JavascriptExecutor = JSExecutor();
        JavascriptExecutor.executeScript(
                "document.querySelector(\"#new_report_custom_report_template > div.layout-report.well > ul\").appendChild(document.querySelector('"
                        + selector + "').cloneNode(true))");
        JavascriptExecutor.executeScript("var parent,child");
        JavascriptExecutor.executeScript(
                "parent = document.querySelector('#new_report_custom_report_template > div.layout-report.well > ul > li:nth-child("
                        + id + ")')");
        JavascriptExecutor.executeScript("child =  parent.querySelectorAll(':scope input')");
        JavascriptExecutor.executeScript("child[2].setAttribute('value'," + id + ")");
        return true;
    }

    public JavascriptExecutor JSExecutor() {

        WebDriver driver = getDriver();
        JavascriptExecutor JavascriptExecutor = ((JavascriptExecutor) driver);
        return JavascriptExecutor;
    }



    /**
     * Description: damos click mediante JS a un web element dado como parámetro
     */
    public void executeJSClick(WebElement element) {

        try {

            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click()", Arrays.asList(element));

        }catch(Exception e) {

            getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
            System.out.println("excepcion recibida: " + e.getMessage());
            getDriver().navigate().refresh();
            assert false;
        }

    }

    /**
     * MÉTODOS PARA USAR EL IMPORTADOR
     *
     * Esta funcion busca si un archivo existe en una carpeta, luego lo borra TODO:
     * Se debe actualizar para descargar y usar los archivos descargados dentro de
     * la carpeta del proyecto, asi no usaremos path distintos
     *
     *
     * @param downloadPath es la ruta de descargas de cada uno de nosotros
     * @param fileName     es el archivo que se busca en la carpeta
     * @return devuelve un verdadero si el archivo es encontrado
     */

    public Boolean isFileDownloaded(String downloadPath, String fileName) {

        System.out.println("downloadPath -> " + downloadPath);

        File dir = new File(downloadPath);

        boolean archivoExiste =  false, finBucle = false, archivoDescargandose = false;
        File[] dirContents = null;
        while (!finBucle) {
            if ( dir.listFiles() != null) {
                finBucle = true;
            }
        }

        finBucle = false;
        String downloaderFile = "";
        int position = 0;
        while (!finBucle) {

            dirContents = dir.listFiles();
            for (int i = 0; i <= dirContents.length -1; i++) {
                archivoDescargandose = true;
                if (dirContents[i].getName().toString().equals(fileName)){
                    downloaderFile = dirContents[i].getName().toString();
                    finBucle = true;
                    position = i;
                    System.out.println("Archivo encontrado: " + dirContents[i].getName().toString());

                    i = dirContents.length -1;
                    archivoDescargandose = false;

                } else if (!dirContents[0].getName().toString().equals(fileName)) {
                    System.out.println("Archivo no encontrado: " + dirContents[i].getName().toString());
                    archivoDescargandose = true;
                }
            }

            if (!archivoDescargandose) {

                finBucle =  true;
                System.out.println("Archivo encontrado y salimos del bucle");
            }
        }

        if (downloaderFile.equals(fileName)) {

            // File has been found, it can now be deleted:

            System.out.println("Archivo si existe: " + dirContents[position].getName().toString());
            dirContents[position].delete();
            archivoExiste = true;

        } else {

            System.out.println("Archivo no  existe en " + downloadPath);
            archivoExiste = false;

        }

        return archivoExiste;

    }

    public Path getRutaLocal() {
        Path userHome = Paths.get(System.getProperty("user.home"));
        return userHome;
    }

    public Path getRutaDownloads(Path userHome) {
        Path downloads = userHome.resolve("Downloads");
        return downloads;
    }

    public void uploadfile(String rutaArchivo, WebElement element) {
        File rutaArchivoSubir = new File(rutaArchivo);
        element.sendKeys(rutaArchivoSubir.getAbsolutePath());

    }

    public Path getRutaRelativaDownloads() {

        String Ruta = getRutaDownloads(getRutaLocal()).toString();
        System.out.println("Ruta Relativa: " + Ruta);
        return getRutaDownloads(getRutaLocal());

    }

    public void clearDirectory(Path path) {

        File dir = new File(path.toString());
        File[] dirContents;

        if (dir.listFiles() == null) {
            System.out.println("No se encontraron archivos a eliminar en directorio: " + dir.getAbsolutePath());
        }

        if (dir.listFiles() != null) {
            dirContents = dir.listFiles();
            for (int i = 0; i < dirContents.length; i++) {
                System.out.println("Archivo a eliminar: " + dirContents[i].toString());
                dirContents[i].delete();
            }

        }

    }

    public void waitForIframeAndSwitchTo(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants_Vars.SHORT_TIMEOUT));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }


    /**
     * ESTOS METODOS DEBERIAN MOVERSE DE LUGAR - SERGIO LOPEZ
     * Encuentra los toast que aparecen en la parte superior derecha de la pantalla, y los cierra
     */
    public void determinaNotificacionConProgressBarPresente() {

        try {
            List<WebElement> closeButtonProcessWithProgressBar = getAllTestObjectsWithXpath("//div[@class='noty_close_button']");
            for (WebElement notification : closeButtonProcessWithProgressBar) {
                if (notification.isDisplayed()) {
                    try {
                        notification.click();
                    } catch (Exception e) {

                    }
                }

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void determinaMensajeHeaderPresente() {
        try {
            List<WebElement> closeButtonAlertHeader = getAllTestObjectsWithXpath("//*[@id='beamerAnnouncementBar']/div[2]/div[2]");
            for (WebElement notification : closeButtonAlertHeader) {
                if (notification.isDisplayed()) {
                    try {
                        notification.click();
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * ESTOS METODOS DEBERIAN MOVERSE DE LUGAR - SERGIO LOPEZ
     */
    public void determinaNotificacionNuevaFuncionalidadPresente() {

       /* WebElement closeButtonNewAdvert = getDriver().findElement(By.xpath("//*[@class='beamerClose']"));

        if (isVisibleElement(5, getDriver().findElement((By) closeButtonNewAdvert))) {

            cerrarNotificacionNuevaFuncionalidad();

        }else {

        }*/

    }

    /**
     * ESTOS METODOS DEBERIAN MOVERSE DE LUGAR - SERGIO LOPEZ
     */
    public void cerrarNotificacionConProgressBar() {

        WebElement closeButtonProcessWithProgressBar = getDriver().findElement(By.xpath("//div[@class='noty_close_button']"));
        closeButtonProcessWithProgressBar.click();

    }

    /**
     * ESTOS METODOS DEBERIAN MOVERSE DE LUGAR - SERGIO LOPEZ
     */
    public void cerrarNotificacionNuevaFuncionalidad() {

        WebElement closeButtonProcessWithProgressBar = getDriver().findElement(By.xpath("//*[@class='beamerClose']"));
        closeButtonProcessWithProgressBar.click();

    }

    public void refreshPage(){
        getDriver().navigate().refresh();
    }


    public void selectOptionByValue(By dropdownLocator, String value) {
        WebElement dropdownElement = getDriver().findElement(dropdownLocator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(value);
    }


    public void selectOptionByValue(WebElement dropDownElement, String value) {
        Select dropdown = new Select(dropDownElement);
        dropdown.selectByValue(value);
    }

    public void waitForElementVisible(By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
        }
    }

    public boolean waitForElementVisible(WebElement element, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true; // Retorna true si el elemento es visible
        } catch (TimeoutException e) {
            return false; // Retorna false si el tiempo de espera se agota y el elemento no es visible
        }
    }

    public String getText(By locator) {
        return getDriver().findElement(locator).getText();
    }



    public void waitForPageLoad() {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants_Vars.DEFAULT_TIMEOUT));
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
            }
        };
        wait.until(pageLoadCondition);
    }

    public void closeNotificationsWithProgressBar() {
        try {
            // Encuentra todas las notificaciones con un botón de cierre usando el WebDriver
            List<WebElement> closeButtonProcessWithProgressBar = getDriver().findElements(By.xpath("//div[@class='noty_close_button']"));
            // Itera sobre cada notificación encontrada
            for (WebElement notification : closeButtonProcessWithProgressBar) {
                // Si la notificación está visible, intenta cerrarla
                if (notification.isDisplayed()) {
                    try {
                        notification.click();
                    } catch (ElementClickInterceptedException e) {
                        // El elemento no es clickeable, puedes manejar este caso aquí si es necesario
                    }
                }
            }
        } catch (Exception e) {
            // Imprime cualquier excepción en la consola
            e.printStackTrace();
        }
    }

    public boolean verifyElementVisible(WebElement element, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));

            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void selectOptionByText(WebElement dropDownElement, String text) {
        Select dropdown = new Select(dropDownElement);
        dropdown.selectByVisibleText(text);
    }
    public void uploadfileAndReplace(String rutaArchivo, WebElement element) {
        File rutaArchivoSubir = new File(rutaArchivo);
        String rutaArchivoModificada = rutaArchivoSubir.getAbsolutePath().replace("/", "\\");
        element.sendKeys(rutaArchivoModificada);

    }

    public void sendKeysAndEnter(WebElement element, String textToType) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants_Vars.DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(textToType);
        element.sendKeys(Keys.ENTER);
    }
    public void sendEnter(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(Constants_Vars.DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(element));
        new Actions(getDriver())
                .sendKeys(Keys.ENTER)
                .perform();
    }


    public String getAtributeID(WebElement element){
        return element.getAttribute("id");
    }
    public boolean isInvisibleElement(WebElement element, long timeoutInSeconds) {
        boolean isInvisible  = true;

        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            isInvisible = false;
        }

        return isInvisible;
    }

}
