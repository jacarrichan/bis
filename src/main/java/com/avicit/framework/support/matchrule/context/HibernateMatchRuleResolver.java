package com.avicit.framework.support.matchrule.context;

import java.io.IOException;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.hibernate.MappingException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

public class HibernateMatchRuleResolver {

	private static final String RESOURCE_PATTERN = "/**/*.class";

	private String[] packagesToScan;

	private ResourcePatternResolver resourcePatternResolver;

	private HibernateMatchRuleFactory factory;

	private static final TypeFilter[] ENTITY_TYPE_FILTERS = new TypeFilter[] {
			new AnnotationTypeFilter(Entity.class, false), new AnnotationTypeFilter(Embeddable.class, false),
			new AnnotationTypeFilter(MappedSuperclass.class, false) };

	public HibernateMatchRuleResolver() {
		this.resourcePatternResolver = ResourcePatternUtils
				.getResourcePatternResolver(new PathMatchingResourcePatternResolver());
		this.factory = new HibernateMatchRuleFactory();
	}

	public HibernateMatchRuleResolver scanPackages(String[] packagesToScan) {
		try {
			for (String pkg : packagesToScan) {
				String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
						+ ClassUtils.convertClassNameToResourcePath(pkg) + RESOURCE_PATTERN;
				Resource[] resources = this.resourcePatternResolver.getResources(pattern);
				MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
				for (Resource resource : resources) {
					if (resource.isReadable()) {
						MetadataReader reader = readerFactory.getMetadataReader(resource);
						String className = reader.getClassMetadata().getClassName();
						if (matchesFilter(reader, readerFactory)) {
							factory.addMatchRuleClass(this.resourcePatternResolver.getClassLoader()
									.loadClass(className));
						}
					}
				}
			}
			HibernateMatchRuleContext.setMatchRuleFactory(factory);
			return this;
		} catch (IOException ex) {
			throw new MappingException("Failed to scan classpath for unlisted classes", ex);
		} catch (ClassNotFoundException ex) {
			throw new MappingException("Failed to load annotated classes from classpath", ex);
		}
	}

	public String[] getPackagesToScan() {
		return packagesToScan;
	}

	public void setPackagesToScan(String[] packagesToScan) {
		for (int i = 0; i < packagesToScan.length; i++) {
			System.out.println(packagesToScan[i]);
		}
		this.packagesToScan = packagesToScan;
		this.scanPackages(packagesToScan);
	}

	private boolean matchesFilter(MetadataReader reader, MetadataReaderFactory readerFactory) throws IOException {
		for (TypeFilter filter : ENTITY_TYPE_FILTERS) {
			if (filter.match(reader, readerFactory)) {
				return true;
			}
		}
		return false;
	}

}
