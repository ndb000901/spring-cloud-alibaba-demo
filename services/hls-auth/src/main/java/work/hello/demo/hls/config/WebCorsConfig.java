package work.hello.demo.hls.config;

@Configuration
public class WebCorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域的路径
                .allowedOrigins("*") // 允许的来源（你也可以写具体域名）
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的 HTTP 方法
                .allowedHeaders("*") // 允许的请求头
                .allowCredentials(false) // 是否允许携带 Cookie
                .maxAge(3600); // 预检请求的缓存时间（秒）
    }
}
