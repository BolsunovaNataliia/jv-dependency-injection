package mate.academy.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.lib.Component;
import mate.academy.lib.Inject;
import mate.academy.lib.Injector;
import mate.academy.model.Product;
import mate.academy.service.FileReaderService;
import mate.academy.service.ProductParser;
import mate.academy.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductParser productParser;
    @Inject
    private FileReaderService fileReaderService;

    @Override
    public List<Product> getAllFromFile(String filePath) {
        Injector injector = Injector.getInjector();
        ProductParser productParser = (ProductParser) injector.getInstance(ProductParser.class);
        FileReaderService fileReaderService
                = (FileReaderService) injector.getInstance(FileReaderService.class);
        return fileReaderService.readFile(filePath)
                .stream()
                .map(productParser::parse)
                .collect(Collectors.toList());
    }
}
