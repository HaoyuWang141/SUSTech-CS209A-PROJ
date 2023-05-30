package sustech.project.javaproject.crawler;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import java.util.ArrayList;
import java.util.List;


public class JavaAPIExtractor {
    public static void main(String[] args) {
        String code = "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "\n" +
                "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello world!\");\n" +
                "\n" +
                "//        int a, b;\n" +
                "//        System.out.println(Math.min(a,b));\n" +
                "\n" +
                "        List<Integer> list = new ArrayList<>();\n" +
                "        list.add(1);\n" +
                "        System.out.println(list.size());\n" +
                "\n" +
                "        int c, d;\n" +
                "        c = 1;\n" +
                "        d = 2;\n" +
                "        System.out.println(Math.max(c,d));\n" +
                "    }\n" +
                "}";

        List<String> list = extractJavaAPIFromCode(code);
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static List<String> extractJavaAPIFromCode(String code) {

        // 使用JavaParser解析代码
        JavaParser javaParser = new JavaParser();
        ParseResult<CompilationUnit> result = javaParser.parse(code);

        NodeList<ImportDeclaration> imports = new NodeList<>();
        if (result.isSuccessful()) {
            CompilationUnit cu = result.getResult().orElse(null);
            if (cu != null) {
                imports = cu.getImports();
            }
        }

        List<String> apis = new ArrayList<>();
        for (Node node : imports) {
            String api = node.toString();
            apis.add(api.substring(7, api.length() - 3));
        }
        return apis;
    }

}
