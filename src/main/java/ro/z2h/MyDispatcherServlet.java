package ro.z2h;

import org.codehaus.jackson.map.ObjectMapper;
import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.fmk.AnnotationScanUtils;
import ro.z2h.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by user on 11/11/2014.
 */
public class MyDispatcherServlet extends HttpServlet {
    HashMap<String,MethodAttributes> hashMap;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("GET", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException  {
        dispatchReply("POST", req, resp);
    }

    private void dispatchReply(String httpMethod, HttpServletRequest req, HttpServletResponse resp) throws IOException {

        /*Dispatch*/
        Object r = dispatch(httpMethod, req, resp);



        /*Reply*/
        reply(r, req, resp);


        /*Erori*/
        Exception ex = null;
        sendException(ex, req, resp);

    }


    /*Where an application controller should be called*/
    private Object dispatch(String httpMethod, HttpServletRequest req, HttpServletResponse resp){
        /*pt /test=Hello
        * pt /employee=> allEmployees de la Application Controller*/

        String pathInfo = req.getPathInfo();
        MethodAttributes methodAttributes = hashMap.get(pathInfo);


        try {
            String controllerClass = methodAttributes.getControllerClass();
            String methodName = methodAttributes.getMethodName();

            Class<?> appController = Class.forName(controllerClass);
            Object appControllerInstance = appController.newInstance();
            Method method= appController.getMethod(methodName);
            Object answer = method.invoke(appControllerInstance);
            return answer;


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return "Hello";
    }

    /*Used to send the view to the client*/
    private void reply(Object r, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        ObjectMapper objectMapper=new ObjectMapper();
        String valueAsString = objectMapper.writeValueAsString(r);
        writer.printf(valueAsString);
    }

    private void sendException(Exception ex, HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("There was an exception");
    }

    @Override
    public void init() throws ServletException {
        hashMap= new HashMap<String, MethodAttributes>();


        Iterable<Class> classes = null;
        try {
            classes = AnnotationScanUtils.getClasses("ro.z2h.controller");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Class aClass : classes) {
            if (aClass.isAnnotationPresent(MyController.class)) {
                String url;

                url = ((MyController) aClass.getAnnotation(MyController.class)).urlPath();

                Method[] method = aClass.getMethods();
                for (Method method1 : method) {
                    if (method1.isAnnotationPresent(MyRequestMethod.class)) {
                        MyRequestMethod method1Annotation = (MyRequestMethod) method1.getAnnotation(MyRequestMethod.class);
                        System.out.println(method1Annotation.urlPath() + method1Annotation.methodType());


                        String urlKey= url+ method1Annotation.urlPath();
                        MethodAttributes methAttributes= new MethodAttributes();
                        methAttributes.setControllerClass(aClass.getName());
                        methAttributes.setMethodName(method1.getName());
                        methAttributes.setMethodType(method1Annotation.methodType());
                        hashMap.put(urlKey, methAttributes);

                    }

                    System.out.println(url);
                    System.out.println(hashMap);

                }

            }
        }
    }
}