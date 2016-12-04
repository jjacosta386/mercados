package co.com.bvc.portal.mercados.servicio.imp;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * The Class XMLCacheBean.
 */
public class XMLCacheBean extends QuartzJobBean
{
	
	/** The spring context. */
	ApplicationContext springContext;

	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException 
	{

	}
}
