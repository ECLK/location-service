from django.urls import include, path
from rest_framework import routers
from src.quickstart import views
from rest_framework_simplejwt import views as jwt_views
from django.contrib import admin
from django.conf import settings
from django.conf.urls.static import static
from src.location import views as location_views

from django.conf.urls import url
from rest_framework_swagger.views import get_swagger_view
from rest_framework.routers import DefaultRouter

schema_view = get_swagger_view(title='Location Service API')

router = DefaultRouter()
router.register('locations', location_views.LocationViewset)
router.register('gdns', location_views.GramaniladariViewset)
router.register('polingdivisions', location_views.PolingdivisionViewset)
router.register('electoraldistrics', location_views.ElectoraldistrictViewset)
router.register('admindistricts', location_views.AdmindistrictViewset)
router.register('img', location_views.MideaItemViewset)
router.register('search', location_views.SearchLocation)
router.register('ministries', location_views.MinistriesViewset)
router.register('provincialcouncils', location_views.ProvincialcouncilsViewset)
router.register('commissions', location_views.CommissionsViewset)
router.register('localauthorities', location_views.LocalAuthoritiesViewset)
router.register('departments', location_views.DepartmentsViewset)
router.register('branches', location_views.BranchesViewset)
router.register('divisionalsecretariats', location_views.DivisionalsecretariatsViewset)
router.register('policedivisions', location_views.PolicedivisionsViewset)
router.register('provincialministries', location_views.ProvincialministriesViewset)
router.register('provicialdepartments', location_views.ProvincialministrydemartmentsViewset)
router.register('institutes', location_views.InstituteViewset)

urlpatterns = [
    path('admin/', admin.site.urls),
    path('token/', jwt_views.TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('token/refresh/', jwt_views.TokenRefreshView.as_view(), name='token_refresh'),
    path('doc/', schema_view)
]

urlpatterns += router.urls

if settings.DEBUG:
  urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)